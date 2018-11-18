package laddergame.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {

	public static final String NEW_LINE = System.lineSeparator();

	private LadderGameInfo ladderGameInfo;
	private Lines lines;

	public Ladder(LadderGameInfo ladderGameInfo, Lines lines) {
		this.ladderGameInfo = ladderGameInfo;
		this.lines = lines;
	}

	public DisplayLadder display() {
		return new DisplayLadder(ladderGameInfo, lines);
	}

	public LadderResult start(String playerName) {
		int playerIndex = ladderGameInfo.getPlayerIndex(playerName);
		return getLadderResult(playerIndex);
	}

	public LadderFinalResult end() {
		int startIndex = 0;
		List<LadderResult> ladderResults = IntStream
				.range(startIndex, ladderGameInfo.getPlayerCount())
				.mapToObj(this::getLadderResult)
				.collect(Collectors.toList());
		return new LadderFinalResult(ladderResults);
	}

	private LadderResult getLadderResult(int playerIndex) {
		int resultIndex = lines.move(playerIndex);
		return new LadderResult(ladderGameInfo.getPlayer(playerIndex),
				ladderGameInfo.getResult(resultIndex));
	}
}
