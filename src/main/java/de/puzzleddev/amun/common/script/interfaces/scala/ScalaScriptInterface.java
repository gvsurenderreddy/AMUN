package de.puzzleddev.amun.common.script.interfaces.scala;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.script.anno.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@ScriptInterface("scala")
@AmunFactory
public class ScalaScriptInterface extends BaseScriptInterface<ScalaScript>
{
	public ScalaScriptInterface()
	{
		super(ScalaScript::new);
	}
}
