package de.puzzleddev.amun.common.script.interfaces.scala;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@ScriptInterface("scala")
@AMUNFactory
public class ScalaScriptInterface extends BaseScriptInterface<ScalaScript>
{
	public ScalaScriptInterface()
	{
		super(ScalaScript::new);
	}
}
