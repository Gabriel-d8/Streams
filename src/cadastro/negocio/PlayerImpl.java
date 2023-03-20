package cadastro.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cadastro.model.Player;

public class PlayerImpl {

	public boolean checkExistingFile(Path path) {
        boolean ret = false;
        
   try {
	   	Stream<Path> stream = Files.list(path);
	   	Optional<Path> arq = stream.filter(p -> p.toString().endsWith("players.txt")).findAny();                          
          ret = arq.isPresent(); 
        }
   		catch (IOException ex) {
            ex.printStackTrace();
        }
        return ret;
   }
	
	public List<Player> getPlayerList(Path path) throws IOException {
        Stream<String> lines = Files.lines(path);
        List<String> lineList = lines.collect(Collectors.toList());
        List<Player> playerList = new ArrayList<>();
        Iterator it = lineList.listIterator();
        String str = null;
        
        while (it.hasNext()) {
        	str = (String) it.next();
        	String info[] = str.split(",");
        	Player player = new Player();
        	
        	player.setName(info[0]);
        	player.setPosition(info[1]);
        	player.setAge(Integer.parseInt(info[2]));
        	player.setCurrentTeam(info[3]);
        	player.setGoalsScored(Integer.parseInt(info[4]));
        	
        	playerList.add(player);
        }
        	 
        return playerList;
	}
	
	public void printPlayers(List<Player> players) {
		players.stream().forEach(System.out::println);
	}
	
	public void printPlayersTeam(List<Player> players, String team) {
		players.stream().filter(p -> p.getCurrentTeam().equals(team)).forEach(System.out::println);
	}
	
	public void printBestTeamScorers (List<Player> players, String team) {
		players.stream().filter(p -> p.getCurrentTeam().equals(team) && p.getGoalsScored() > 10).forEach(System.out::println);
	}
	
	public void groupPlayersByTeam (List<Player> players) {
		players.stream().sorted(Comparator.comparing(Player::getCurrentTeam)).forEach(System.out::println);
	}

	public double calculateAverageAge(List<Player> players) {
		return players.stream().mapToInt(Player::getAge).average().getAsDouble();
	}

	public void olderPlayer(List<Player> players) {
		Player player = players.stream().max(Comparator.comparingInt(Player::getAge)).get();
		System.out.println("Player older: " + player.getName() + ", " + player.getAge());
	}
	
	public void youngestPlayer(List<Player> players) {
		Optional<Player> player = players.stream().min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
		System.out.println("Youngest player: " + player.get().getName() + ", " + player.get().getAge());
	}
	
	public void topScorePlayer(List<Player> players) {
		Player player = players.stream().max(Comparator.comparingInt(Player::getGoalsScored)).get();
		System.out.println("Top Score Player: " + player.getName() + ", " + player.getGoalsScored() + " goals");
	}
	
	public int sumOfGoals(List<Player> players) {
		int sum = players.stream().mapToInt(p -> p.getGoalsScored()).sum();
		return sum;
	}
	
	public void groupByTeam(List<Player> players) {
		Map<String, List<Player>> group = players.stream().collect(Collectors.groupingBy(Player::getCurrentTeam));
		System.out.println(group);
	}
	
	public void rankingTopScorePlayers(List<Player> players) {
		players.stream().sorted(Comparator.comparingInt(Player::getGoalsScored).reversed()).forEach(System.out::println);
	}
	
}	