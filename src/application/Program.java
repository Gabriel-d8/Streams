package application;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import cadastro.model.Player;
import cadastro.negocio.PlayerImpl;

public class Program {

	public static void main(String[] args) throws IOException {

		PlayerImpl player = new PlayerImpl();
		
		String directoryAdress = "C:\\temp";
		String archiveName = "players.txt";
		
		List<Player> playersList = player.getPlayerList(Paths.get(directoryAdress + "//" + archiveName));
		
		player.rankingTopScorePlayers(playersList);
		System.out.println();
		player.calculateAverageAge(playersList);
		System.out.println();
		player.printPlayers(playersList);
		System.out.println();
		player.sumOfGoals(playersList);
		System.out.println();
			
		String team = "Sao Paulo";
		player.printBestTeamScorers(playersList, team);
		System.out.println();
		player.printPlayersTeam(playersList, team); 
		
		player.topScorePlayer(playersList);
		
	}

}