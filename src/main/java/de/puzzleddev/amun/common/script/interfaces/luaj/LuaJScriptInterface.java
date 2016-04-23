package de.puzzleddev.amun.common.script.interfaces.luaj;

import de.puzzleddev.amun.common.anno.check.ClassLoadedCheck;
import de.puzzleddev.amun.common.anno.construct.AmunCheck;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.script.anno.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@AmunCheck(check = ClassLoadedCheck.class, data = LuaJScriptInterface.LUAJ_CHECK_CLASS)
@ScriptInterface("lua")
@AmunFactory
public class LuaJScriptInterface extends BaseScriptInterface<LuaJScript>
{
	public static final String LUAJ_CHECK_CLASS = "org.luaj.vm2.lib.jse.JsePlatform";

	public LuaJScriptInterface()
	{
		super(LuaJScript::new, LUAJ_CHECK_CLASS);
	}
}
