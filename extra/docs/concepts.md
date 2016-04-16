# Concepts
This is about the high concepts of Amun.<br>

---

Amun was originally started from code extracted from the first mod I planned to release on my own but as the codebase grew and grew I decided that it was a better idea to just make a core mod containing all of this code, so I did.<br>
This leads us to the first aspect:

## Structure
Amun is separated into four different parts:
1. Common
2. Client
3. Server
4. Util

Each of them with their own package structure branching of off `de.puzzleddev.amun`<br>
* Common contains most of the code including the main core classes.
* Client is reserved for client only code such as resources and guis.
* Server won't contain a lot of code because only a few things are server only.
* Util also contains a considerable amount of code, because it contains all of the supporting technology.
