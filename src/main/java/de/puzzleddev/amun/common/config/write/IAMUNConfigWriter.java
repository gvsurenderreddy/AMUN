package de.puzzleddev.amun.common.config.write;

import java.io.OutputStream;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.util.WIP;

@WIP
public interface IAMUNConfigWriter
{
	public void writeConfig(OutputStream out, IAMUNConfig conf);
}
