ChangeSilkTouch 
Version 1.2

Introduction
------------

This plugin allows you to change how the SilkTouch enchant functions. It allows
you to choose what blocks are affected, and what blocks are dropped when those
blocks are mined. It is managed by a simple configuration file in yml; the 
same style as most plugins. By default, it will allow you to mine spawners and
ice blocks again.


Installation
------------

Simply drop ChangeSilkTouch.jar into your plugins folder, restart the server, 
and you should be set to go!

If you want to change what blocks are affected by the plugin, you will need to 
modify the configuration file in the ChangeSilkTouch folder. 


Configuration
-------------

The configuration for this plugin is quite simple. The configuration file
currently has just one variable in it, which contains a list of all affected 
blocks. An example is shown below: 


blocks:
  # Ice 
  - blockType: 79
    dropType: 79
    count: 1
  # Monster Spawner
  - blockType: 52

The # is a comment, and can be left out. This is just to keep track of what 
block is what.

blockType is the identity of the block that has to be mined. It is required.

dropType is the type of block that is dropped when this block is mined. It can
be left out, and defaults to blockType.

count is how many blocks are dropped. It can be left out, and defaults to 1.


Support & Bug Reports
---------------------

You have a few options for this. The first is by directly emailing me. My email address is owner@cpprograms.net. 

Your other option is the thread on the Bukkit forums.

Either way, I will try to get back to you as soon as is reasonably possible.


Latest Version
--------------

The latest version of this will always be available at http://www.cpprograms.net.
