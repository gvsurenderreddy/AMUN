package de.puzzleddev.amun.network.message;

import java.io.IOException;

import com.google.common.base.Throwables;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Wrapper around the IMessage interface.
 * 
 * @author tim4242
 */
public interface IAmunMessage extends IMessage
{
	public void read(PacketBuffer buf) throws IOException;

	public void write(PacketBuffer buf) throws IOException;
	
	@Override
	public default void fromBytes(ByteBuf buffer)
	{
		try
		{
			read(new PacketBuffer(buffer));
		} catch(IOException e)
		{
			throw Throwables.propagate(e);
		}
	}

	@Override
	public default void toBytes(ByteBuf buffer)
	{
		try
		{
			write(new PacketBuffer(buffer));
		} catch(IOException e)
		{
			throw Throwables.propagate(e);
		}
	}
}
