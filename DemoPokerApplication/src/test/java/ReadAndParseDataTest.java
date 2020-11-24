
import com.demo.poker.model.Game;
import com.demo.poker.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Deividas
 */
@SpringBootTest
public class ReadAndParseDataTest {

  private final String URL_STR = "https://projecteuler.net/project/resources/p054_poker.txt";

  public ReadAndParseDataTest() {
  }

  @Test
  public void readFile() throws Exception {
    List<String> lines = Utils.readData(URL_STR);
    System.out.println(lines);
  }

  @Test
  public void parse() throws Exception {
    final List<Game> games = new ArrayList<>();
    List<String> lines = Utils.readData(URL_STR);
    lines.stream().forEach(line -> games.add(Utils.parseLineAndFillGame(line)));
    System.out.println(games);
  }
}
