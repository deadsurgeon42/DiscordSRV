/*
 * DiscordSRV - A Minecraft to Discord and back link plugin
 * Copyright (C) 2016-2017 Austin Shapiro AKA Scarsz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package github.scarsz.discordsrv.commands;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import github.scarsz.discordsrv.util.LangUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandLink {

    @Command(commandNames = { "link" },
            helpMessage = "Generates a code to link your Minecraft account to your Discord account",
            permission = "discordsrv.link"
    )
    public static void execute(Player sender, String[] args) {
        if (DiscordSRV.getPlugin().getAccountLinkManager() == null) {
            sender.sendMessage(ChatColor.RED + LangUtil.InternalMessage.UNABLE_TO_LINK_ACCOUNTS_RIGHT_NOW.toString());
            return;
        }

        if (DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(sender.getUniqueId()) != null) {
            sender.sendMessage(ChatColor.AQUA + LangUtil.InternalMessage.ACCOUNT_ALREADY_LINKED.toString());
        } else {
            String code = DiscordSRV.getPlugin().getAccountLinkManager().generateCode(sender.getUniqueId());

            sender.sendMessage(ChatColor.AQUA + LangUtil.Message.CODE_GENERATED.toString()
                    .replace("%code%", code)
                    .replace("%botname%", DiscordSRV.getPlugin().getMainGuild().getMember(DiscordUtil.getJda().getSelfUser()).getEffectiveName())
            );
        }
    }

}
