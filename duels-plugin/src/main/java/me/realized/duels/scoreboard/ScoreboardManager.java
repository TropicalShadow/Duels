package me.realized.duels.scoreboard;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.match.DuelMatch;
import me.realized.duels.party.PartyManagerImpl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.UUID;

public class ScoreboardManager {
    private final DuelsPlugin plugin;
    private final DuelMatch match;
    private final PartyManagerImpl partyManager;

    private Scoreboard scoreboardTeamOne;
    private Scoreboard scoreboardTeamTwo;

    public ScoreboardManager(final DuelsPlugin plugin, final DuelMatch match, final PartyManagerImpl partyManager) {
        this.plugin = plugin;
        this.match = match;
        this.partyManager = partyManager;
        scoreboardTeamOne = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboardTeamTwo = Bukkit.getScoreboardManager().getNewScoreboard();
    }


    public void registerScoreboards(final Collection<Player> players, final Collection<Player> others){
        Team alliesTeamOne = scoreboardTeamOne.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));
        Team enemiesTeamOne = scoreboardTeamOne.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));
        Team party = scoreboardTeamOne.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));

        Team alliesTeamTwo = scoreboardTeamTwo.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));
        Team enemiesTeamTwo = scoreboardTeamTwo.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));
        Team partyTwo = scoreboardTeamTwo.registerNewTeam(UUID.randomUUID().toString().substring(0, 15));


        String alliesPrefix = plugin.getLang().getMessage("PREFIX.ALLIES");
        String enemiesPrefix = plugin.getLang().getMessage("PREFIX.ENEMIES");

        alliesTeamOne.setPrefix(alliesPrefix);
        enemiesTeamOne.setPrefix(enemiesPrefix);

        alliesTeamTwo.setPrefix(alliesPrefix);
        enemiesTeamTwo.setPrefix(enemiesPrefix);


        alliesTeamOne.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        enemiesTeamOne.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        alliesTeamTwo.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        enemiesTeamTwo.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        players.forEach(player -> {
            alliesTeamOne.addEntry(player.getName());
            enemiesTeamTwo.addEntry(player.getName());
            player.setScoreboard(scoreboardTeamOne);
        });
        others.forEach(player -> {
            enemiesTeamOne.addEntry(player.getName());
            alliesTeamTwo.addEntry(player.getName());
            player.setScoreboard(scoreboardTeamTwo);
        });


    }

    public void unregisterScoreboards(final Collection<Player> players){
        scoreboardTeamOne.getTeams().forEach(Team::unregister);
        scoreboardTeamTwo.getTeams().forEach(Team::unregister);
        players.forEach(player -> {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        });
        scoreboardTeamOne = null;
        scoreboardTeamTwo = null;
    }


    private boolean isInSameParty(final Player player, final Player other) {
        return (partyManager.isInParty(player) && partyManager.isInParty(other)) && partyManager.get(player).getOwner().getUuid() == partyManager.get(other).getOwner().getUuid();
    }

}
