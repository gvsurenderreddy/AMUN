# <u><b>AMUN</b></u>
## AMUN is a mod for Minecraft, it provides common functionality for other mods.

## Table of Contents

* [About](#about)
* [Goals](#goals)
* [Installation](#installation)
* [License](#license)

## About

AMUN is a mod for Minecraft providing common functionality for other mods (specifically mods by SophosMZ, or its developers, but of course it can be used by anyone).

## Goals

* Making modding at any level **easier**
* Making development **fast**
* Making mod code **elegant**
* Making a library that can be modified and used by **anyone**
* Bringing more **modern** approaches to software design to Minecraft

It's main goal is making development as fast and easy as possible but still flexible.

The key word in the previous paragraph is "*flexible*", every part of AMUN is configurable, from configuration formats to compatibility modules.

One other way AMUN wants to make itself configurable in the future is a scripting interface for adding functionality to it as a end user, or in "functionality packs".

## Installation

Just put this mod into the "minecraft/mods" folder, all dependent assets will be downloaded when you first launch the game when a mod is active that depends on assets.

If you expect to play the game offline, you can download the assets from a location provided by the mod author, then put them into the "minecraft/amun" folder.

These dependencies are weak, so you can disable them. But they could enhance your experience.

## Building

1. Clone this repository using
  - `git clone https://github.com/SophosMZ/AMUN.git` or
  - `git clone git@github.com:SophosMZ/AMUN.git`

2. Run `make` or `bash make`

Alternatively you can also use the provided archive, it should contain everything
you are interested in.

## License

* Source code and Binaries
  - [![License](https://img.shields.io/badge/License-Apache%20License%202.0-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0)

* Textures and Models
  - [![License](https://img.shields.io/badge/License-CC%20BY%203.0-brightgreen.svg?style=flat)](https://creativecommons.org/licenses/by/3.0/)

* Everything else
  - [![License](https://img.shields.io/badge/License-No%20Restriction-green.svg?style=flat)](https://creativecommons.org/publicdomain/zero/1.0/)
