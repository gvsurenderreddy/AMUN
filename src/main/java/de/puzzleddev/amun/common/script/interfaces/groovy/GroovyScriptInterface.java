package de.puzzleddev.amun.common.script.interfaces.groovy;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@ScriptInterface("groovy")
@AMUNFactory
public class GroovyScriptInterface extends BaseScriptInterface<GroovyScript>
{
	public static final String GROOVY_CHECK_CLASS = "groovy.lang.GroovyClassLoader";
	
	public GroovyScriptInterface()
	{
		super(GroovyScript::new, GROOVY_CHECK_CLASS);
	}
}
