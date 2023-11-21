import kotlin.random.Random
val colors = listOf('R', 'B', 'Y', 'G', 'O', 'W')
fun main() {


    // Step 1: First player randomly chooses four letters
    val secretCode = (1..4).map { colors.random() }
//println("secret code is : $secretCode")
    println("Computer it is your first player it has selected 4 colors randomly  in\t [${colors.joinToString()}] ")

    // Step 2: Second player makes guesses until correct
    var correctGuess = false
    var attempts = 0

    while (!correctGuess) {
        // Step 3: Second player submits a guess
        val guess = getUserGuess()

        // Step 3: First player provides feedback
//        val feedback = provideFeedback(secretCode, guess)
//        println("Feedback: $feedback")

        // Check if the guess is correct
        val feedback = provideFeedback(secretCode, guess)

        println("Attempt ${++attempts} (Player 2): ${guess.joinToString()}")
        println("Feedback: Correct Position -> ${feedback.first}, Correct Color -> ${feedback.second}\n")

        if (feedback.first == 4) {
            println("Congratulations! Player 2 you have guessed the correct code. computer code was :$secretCode and yours is :$guess ")
            break
        }

    }

    println("Congratulations! You guessed the code in $attempts attempts.")
}

fun getUserGuess(): List<Char> {
    while (true) {
        print("Enter your guess (e.g., RBYG): ")
        val guessInput = readLine()?.toUpperCase() ?: ""

        if (guessInput.length != 4 || guessInput.any { it !in colors }) {
            println("Invalid input. Please enter exactly four valid colors from: ${colors.joinToString()}")
            continue
        }

        // If input is valid, return the filtered list
        return guessInput.filter { it in "RBYGOW" }.toList()
    }
}

fun provideFeedback(secretCode: List<Char>, guess: List<Char>): Pair<Int, Int>  {
    val feedback = mutableListOf<Char>()
    var correctColor  = 0
    var correctPosition = 0
    for (i in secretCode.indices) {
        if (guess[i] == secretCode[i]) {
            correctPosition++ // Correct color and position
        }
        if (guess[i] in secretCode) {
            correctColor++    // Correct color but wrong position
        }
    }


    return Pair(correctPosition,correctColor)
}