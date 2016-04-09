package de.puzzleddev.amun.common.script.interfaces.groovy;

import de.puzzleddev.amun.common.anno.check.ClassLoadedCheck;
import de.puzzleddev.amun.common.anno.construct.AmunCheck;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.ScriptInterface;
import de.puzzleddev.amun.common.script.interfaces.BaseScriptInterface;

@AmunCheck(check = ClassLoadedCheck.class, data  = GroovyScriptInterface.GROOVY_CHECK_CLASS)
@ScriptInterface("groovy")
@AmunFactory
public class GroovyScriptInterface extends BaseScriptInterface<GroovyScript>
{
	public static final String GROOVY_CHECK_CLASS = "groovy.lang.GroovyClassLoader";
	
	public GroovyScriptInterface()
	{
		super(GroovyScript::new, GROOVY_CHECK_CLASS);
	}
}
