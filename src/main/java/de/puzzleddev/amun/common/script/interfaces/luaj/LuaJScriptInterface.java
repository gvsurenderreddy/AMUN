package de.puzzleddev.amun.common.script.interfaces.luaj;

import de.puzzleddev.amun.common.anno.check.ClassLoadedCheck;
import de.puzzleddev.amun.common.anno.construct.AMUNCheck;
import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@AMUNCheck(check = ClassLoadedCheck.class, data = LuaJScriptInterface.LUAJ_CHECK_CLASS)
@ScriptInterface("lua")
@AMUNFactory
public class LuaJScriptInterface extends BaseScriptInterface<LuaJScript>
{
	public static final String LUAJ_CHECK_CLASS = "org.luaj.vm2.lib.jse.JsePlatform";
	
	public LuaJScriptInterface()
	{
		super(LuaJScript::new, LUAJ_CHECK_CLASS);
	}
}
