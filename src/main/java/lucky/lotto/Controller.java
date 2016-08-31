package lucky.lotto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.primitives.Ints;

@RestController
@RequestMapping(value = "/lotto", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Autowired
    private Manager manager;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, String> changeDoorStatus(@RequestBody Map<String, Integer> names,
            @RequestParam(name = "clean", defaultValue = "false") String isClean,
            @RequestParam(name = "num", defaultValue = "10") String num,
            @RequestParam(name = "multipleWins", defaultValue = "false") String multiplewins) {
        Integer numWinners = Ints.tryParse(num);
        numWinners = numWinners != null ? numWinners : 10;

        Map<Integer, String> testMap = new HashMap<>();
        if (names != null && !names.isEmpty()) {
            testMap.putAll(manager.pickWinners(names, numWinners, Boolean.valueOf(isClean),
                    Boolean.valueOf(multiplewins)));
        }

        return testMap;
    }

}
