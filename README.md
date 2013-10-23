<h1>AutoRefill</h1>
<p>A Bukkit plugin that refills the item in a player's hand when they have ran out. The refilled items come from that player's inventory.</p>
<p>Version: v1.0</p>

<h2>Installing & Upgrading</h2>
<ol>
  <li><a href="http://dev.bukkit.org/bukkit-plugins/refill/">Download</a> AutoRefill.zip</li>
  <li>Unzip Refill.zip.</li>
  <li>Drag AutoRefill.jar into your "craftbukkit/plugins" folder <em>&ndash; replace AutoRefill.jar when upgrading.</em></li>
  <li>Reload the craftbukkit server.</li>
</ol>
<p>NOTE: If you made changes to the "config.yml" file, your changes will NOT be overrode by updating the plugin.</p>

<h2>Commands</h2>
<p>AutoRefill is very simple to use, in fact there are only two commands!</p>
<ul>
  <li><b>/refill [on/off]</b> <em>&ndash; this turns AutoRefill on or off.<em></li>
  <li><b>/refill [player] [on/off]</b> <em>&ndash; this turns AutoRefill on or off for a specific player.<em></li>
</ul>
<p>Please note that AutoRefill is <b>disabled by default.</b></p>

<h2>Permissions</h2>
<ul>
  <li><b>refill.* &ndash;</b> enables all permissions.</li>
  <li><b>refill.use &ndash;</b> AutoRefill may be used by this player. <em>Note: Refill must be turned on first (either by using a command or by enabling "on-by-default" in config.yml).</em></li>
  <li><b>refill.on &ndash;</b> enables use of "/refill on" command.</li>
  <li><b>refill.off &ndash;</b> enables use of "/refill off" command.</li>
  <li><b>refill.player.on &ndash;</b> enables use of "/refill [player] on" command.</li>
  <li><b>refill.player.off &ndash;</b> enables use of "/refill [player] off" command.</li>
</ul>

<h2>Configuration</h2>
<p>AutoRefill may be configured from the default settings.</p>
<h3>You Can:</h3>
<ul>
  <li>Turn Refill on or off by default</li>
  <li>Change which blocks that can be refilled.</li>
</ul> 

<h3>Important Notes:</h3>
<ul>
  <li>Navigate to the "config.yml" file in the "craftbukkit/plugins/Refill" folder. If the file or folder is not present, reload the craftbukkit server.</li>
  <li>Only use space characters in the config.yml file. Tabs will break it!</li>
  <li><b>Blocks must be specified by their material name.</b> These values can be found <a href="http://jd.bukkit.org/dev/apidocs/org/bukkit/Material.html" target="_blank">here.</a></li>
  <li>Craftbukkit must be reloaded before any changes to the config.yml file can take place.</li>
</ul>

<h2>Future Releases</h2>
<ul>
   <li>Add additional block data &ndash; When refilling oak logs, it won't refill a different type (like spruce logs).</li>
</ul>

<h2>Credits</h2>
<p>Author: silvermmonkey A.K.A. <a href="http://forums.bukkit.org/members/mmonkey.90802399/">mmonkey</a><br>
Testers: Desaxt01, GrahamCracker4m, HuskerMath and e_dick.</p>

<h2>ChangeLog</h2>
<ul>
  <li>v1.0 <em>&mdash; Initial release.</em></li>
</ul>