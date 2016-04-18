# <u><b>AMUN</b></u>
## AMUN is a mod for Minecraft, it provides common functionality for other mods.

## Table of Contents

* [About](#about)
* [Goals](#goals)
* [Installation](#installation)
* [Issues](#issues)
* [License](#license)

## About

AMUN is a mod for Minecraft providing common functionality for other mods (specifically mods by SophosMZ, or its developers, but of course it can be used by anyone).

It also claims to be the most commented mod in existence, 2018/10441 lines are commented.

At the moment AMUN is under heavy development, no branch has any guarantee of working or even launching. This may change in the near future but at the moment there is no definite launch date.

## Goals

* Making modding at any level **easier**
* Making development **fast**
* Making mod code **elegant**
* Making a library that can be modified and used by **anyone**
* Bringing more **modern** approaches to software design to Minecraft

It's main goal is making development as fast and easy as possible but still flexible.

The key word in the previous paragraph is "*flexible*", every part of AMUN is configurable, from configuration formats to compatibility modules.

One other way AMUN wants to make itself configurable in the future is a scripting interface for adding functionality to it as a end user, or in "functionality packs".

If you want a more specific (but not exhaustive) list, have one:

- [x] Flexible annotation system
- [x] Extensible scripting system
- [ ] Extensive mod integration (partly done)
- [ ] Easy to use GUI framework
- [ ] Easy to implement but adaptable in world networks
- [ ] Annotation based wrapper for networking
- [x] Flexible file serialization / deserialization
- [x] Extensible recipe system, that automatically integrates with recipe mods (partly done)
- [ ] More to come...

## Installation

Just put this mod into the "minecraft/mods" folder, all dependent assets will be downloaded when you first launch the game when a mod is active that depends on assets.

If you expect to play the game offline, you can download the assets from a location provided by the mod author, then put them into the "minecraft/amun" folder.

These dependencies are weak, so you can disable them. But they could enhance your experience.

## Building

1. Clone this repository using
  - `git clone https://github.com/SophosMZ/AMUN.git` or
  - `git clone git@github.com:SophosMZ/AMUN.git`

2. Run `make` on Windows or `bash make` on \*nix and Mac systems.  

Alternatively you can also use the provided archive, it should contain everything you are interested in.

## Issues

1. Bug reports

  - When committing bug reports (and want us to help you) we need a bit information about the bug, simply sending `It doesn't work fix it!!1!!` doesn't help. We won't outright reject them but we will ask you to read this and add more information to the report.<br>
  To make it easer for you to make a good report I have made a checklist for what to do:

    1. Use common sense, is AMUN really causing the issue.
    2. Make sure you are using a supported version (when a version gets old we will stop to support it), and that you are using the newest non-beta version for the version you are playing.
    3. See if the bug you are experiencing hasn't already been answered or fixed.
    4. Go to the [issue page](https://github.com/SophosMZ/AMUN/issues) and click on [new issue](https://github.com/SophosMZ/AMUN/issues/new).
      1. Don't add any tags to it, we'll take care of that.
      2. Give your issue a sensible title (`It doesn't work` or `Found a bug` doesn't count). The title should summarize the issue (`AMUN crashes on launch`).
      3. Include the necessary details:
          - Minecraft version
          - Mod version
          - Forge version
          - The crash log, but don't just paste it in the report, instead upload it to a site like [pastebin.com](https://pastebin.com) or [pastee.org](https://pastee.org) and link it in the report.<br>
          This log is located at "minecraft/crash-reports/crash-DATE_TIME-client/server.txt"
          - The full log, for this the same rules apply. It is located at "minecraft/logs/fml-client-latest.log" (This isn't really necessary but sometimes we'll ask for it).
          - When you are reporting an issue between AMUN and another mod (or what you suspect to be) also include the mod list.
          - A detailed description of the bug.
          - A detailed explanation on how to reproduce the bug.
      4. Some other detailed are nice to have but not necessary, please try to include the following if applicable
          - What did you expect to happen
          - Did it happen on a server or in single player
          - Screenshots of the problem
          - The modpack you are using and the version of it
            - You should also file a report to the pack author

    5. Click Submit `New Issue` and wait for feedback!

2. Suggestions

  - Suggestions can be anything from new features to a new code format (the latter will be rejected in most cases, but it is still valid).<br>
  When filing such an "issue" you should also consider some things:
    1. Is this feature already implemented
    2. Is this reasonably possible to do with just vanilla Minecraft and this mod (making a redstone computer instead on a modded one isn't reasonable).
    3. Is it a major feature of a major mod.
  - If none of the above applies, you are good to go:
    1. Go to the [issue page](https://github.com/SophosMZ/AMUN/issues) and click on [new issue](https://github.com/SophosMZ/AMUN/issues/new).
      1. Don't add any tags to it, we'll take care of that.
      2. Give it a descriptive title (`new feature` or `NEW FEATURE MUST BE IMPLEMENTED!!1!!` doesn't count).
      3. Include all details that are needed, there isn't a "too long".

## License

* Source code and Binaries
  - [![License](https://img.shields.io/badge/License-Apache%20License%202.0-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0)

* Textures and Models
  - [![License](https://img.shields.io/badge/License-CC%20BY%203.0-brightgreen.svg?style=flat)](https://creativecommons.org/licenses/by/3.0/)

* Everything else
  - [![License](https://img.shields.io/badge/License-No%20Restriction-green.svg?style=flat)](https://creativecommons.org/publicdomain/zero/1.0/)

## Comments

  All comment measurements are taken with this program.
  Which, ironically isn't commented.

```java

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.Arrays;

public class CommentCounter
{
	private static long LINES = 0;
	private static long COMMNENT_LINES = 0;

	private static boolean IN_MULTILINE = false;

	private static void readLines(File f) throws Exception
	{
		System.out.println("Searching in file " + f);

		Files.lines(f.toPath()).forEach((_line) ->
		{
			String line = _line.trim();

			LINES++;

			if(line.endsWith("*/"))
			{
				IN_MULTILINE = false;
				COMMNENT_LINES++;
			}
			else if(line.startsWith("/*"))
			{
				IN_MULTILINE = true;
				COMMNENT_LINES++;
			}
			else if(IN_MULTILINE)
			{
				COMMNENT_LINES++;
			}
			else if(line.startsWith("//"))
			{
				COMMNENT_LINES++;
			}
		});

		IN_MULTILINE = false;
	}

	private static void iterateFolder(File root, FilenameFilter filter) throws Exception
	{
		System.out.println("Iterating over directory "  + root);

		for(File f : root.listFiles(filter))
		{
			if(f.isFile())
			{
				readLines(f);
			}
		}

		for(File f : root.listFiles())
		{
			if(f.isDirectory())
			{
				iterateFolder(f, filter);
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(Arrays.asList(args));

		File root = new File(args[0]);
		FilenameFilter filter = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				return true;
			}
		};

		if(args.length >= 2)
		{
			filter = new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					return name.endsWith("." + args[1]);
				}
			};
		}

		if(root.exists() && root.isDirectory())
		{
			iterateFolder(root, filter);
		}
		else
		{
			System.out.println(root + " isn't a directory");
			System.exit(-1);
		}

		System.out.printf("%d/%d are commented", COMMNENT_LINES, LINES);
	}
}

```
