package de.puzzleddev.amun.client.gui;

import java.util.Collection;
import java.util.Optional;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.RegisterEventHandler;
import de.puzzleddev.amun.util.functional.Function.VoidOneArg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@RegisterEventHandler(RegisterEventHandler.FORGE_EVENT_BUS)
public class GuiModification
{
	private Collection<IGuiScreenRenderable> m_renderable;
	private Collection<IGuiOpenHandler> m_openers;

	private class IncludedButton
	{
		public Class<? extends GuiScreen> m_type;
		public int m_x, m_y;
		public String m_title;
		public VoidOneArg<Minecraft> m_func;

		public int m_id = -1;
	}

	private Collection<IncludedButton> m_buttons;

	private static GuiModification m_instance;

	@AmunFactory
	public static GuiModification instance()
	{
		if(m_instance == null)
			m_instance = new GuiModification();

		return m_instance;
	}

	private GuiModification()
	{
		m_renderable = Lists.newArrayList();
		m_openers = Lists.newArrayList();
		m_buttons = Lists.newArrayList();

		addOpener((screen, buttons) -> {

			for(IncludedButton i : m_buttons)
			{
				if(i.m_type.equals(screen.getClass()))
				{
					Optional<GuiButton> b = buttons.stream().max((o1, o2) -> {
						return Integer.compare(o1.id, o2.id);
					});
					
					if(b.isPresent())
					{
						i.m_id = b.get().id + 1;
						
						buttons.add(new GuiButton(i.m_id, i.m_x, i.m_y, 20, screen.mc.fontRendererObj.getStringWidth(i.m_title) + 20, i.m_title));
					}
				}
			}

		});
	}

	public void addRenderable(IGuiScreenRenderable renderable)
	{
		m_renderable.add(renderable);
	}

	public void addOpener(IGuiOpenHandler open)
	{
		m_openers.add(open);
	}

	@SubscribeEvent
	public void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event)
	{
		for(IGuiOpenHandler r : m_openers)
		{
			r.onOpen(event.gui, event.buttonList);
		}
	}

	@SubscribeEvent
	public void onRender(GuiScreenEvent.DrawScreenEvent.Post event)
	{
		for(IGuiScreenRenderable r : m_renderable)
		{
			r.renderInGui(event.gui, event.mouseX, event.mouseY);
		}
	}

	@SubscribeEvent
	public void onAction(ActionPerformedEvent.Post event)
	{
		for(IncludedButton i : m_buttons)
		{
			if(i.m_type.equals(event.gui.getClass()))
			{
				if(i.m_id > 0)
				{
					if(event.button.id == i.m_id)
					{
						i.m_func.call(event.gui.mc);

						return;
					}
				}
			}
		}
	}

	public void registerButton(Class<? extends GuiScreen> screenType, int x, int y, String title, VoidOneArg<Minecraft> func)
	{
		IncludedButton res = new IncludedButton();

		res.m_type = screenType;
		res.m_x = x;
		res.m_y = y;
		res.m_title = title;
		res.m_func = func;

		m_buttons.add(res);
	}
}
