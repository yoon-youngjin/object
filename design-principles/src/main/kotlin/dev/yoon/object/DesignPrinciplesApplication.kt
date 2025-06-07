package dev.yoon.`object`

class DesignPrinciplesApplication

fun main(args: Array<String>) {
    val game = Game(
        player = Player(
            position = Position(0, 2),
            worldMap = WorldMap.of(
                size = Size(2, 3),
                Room(Position.of(0, 0), "샘", "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다."),
                Room(Position.of(0, 1), "다리", "큰 강 위에 돌로 만든 커다란 다리가 있습니다."),
                Room(Position.of(1, 1), "성", "용왕이 살고 있는 성에 도착했습니다."),
                Room(Position.of(0, 2), "언덕", "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다."),
                Room(Position.of(1, 2), "동굴", "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.")
            ),
        ),
        running = false,
    )
    game.run()
}
