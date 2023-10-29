import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int correctAnswers = 0;
        int totalQuestions = 0;

        System.out.println("¡Bienvenido a la Instrucción Asistida por Computadora!");

        while (totalQuestions < 10) {
            int level = chooseLevel(input);
            int operationType = chooseOperationType(input);
            int num1 = generateNumber(level, random);
            int num2 = generateNumber(level, random);

            int correctAnswer = calculateCorrectAnswer(operationType, num1, num2);
            displayQuestion(num1, num2, operationType);

            int userAnswer = input.nextInt();
            totalQuestions++;

            if (userAnswer == correctAnswer) {
                correctAnswers++;
                displayPositiveFeedback(random);
            } else {
                displayNegativeFeedback(random);
            }
        }

        double percentage = (double) correctAnswers / totalQuestions * 100;
        displayResult(percentage);

        if (percentage >= 75) {
            System.out.println("Felicidades, estás listo para pasar al siguiente nivel!");
        } else {
            System.out.println("Por favor, pide ayuda adicional a tu instructor.");
        }
    }

    public static int chooseLevel(Scanner input) {
        System.out.println("Elige el nivel de dificultad (1-5): ");
        return input.nextInt();
    }

    public static int chooseOperationType(Scanner input) {
        System.out.println("Elige el tipo de operación (1: suma, 2: resta, 3: multiplicación, 4: división, 5: mezcla): ");
        return input.nextInt();
    }

    public static int generateNumber(int level, SecureRandom random) {
        int maxNumber = (int) Math.pow(10, level);
        return random.nextInt(maxNumber);
    }

    public static int calculateCorrectAnswer(int operationType, int num1, int num2) {
        switch (operationType) {
            case 1: // Suma
                return num1 + num2;
            case 2: // Resta
                return num1 - num2;
            case 3: // Multiplicación
                return num1 * num2;
            case 4: // División
                return num1 / num2; // Asumiendo que la división siempre es exacta
            default:
                return 0; // Valor predeterminado, no debería ocurrir
        }
    }

    public static void displayQuestion(int num1, int num2, int operationType) {
        String operator = getOperatorString(operationType);
        System.out.printf("¿Cuánto es %d %s %d? ", num1, operator, num2);
    }

    public static String getOperatorString(int operationType) {
        switch (operationType) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "x";
            case 4:
                return "/";
            default:
                return "";
        }
    }

    public static void displayPositiveFeedback(SecureRandom random) {
        String[] positiveFeedback = {"¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!"};
        int index = random.nextInt(positiveFeedback.length);
        System.out.println(positiveFeedback[index]);
    }

    public static void displayNegativeFeedback(SecureRandom random) {
        String[] negativeFeedback = {"No. Por favor intenta de nuevo.", "Incorrecto. Intenta una vez más.", "¡No te rindas!", "No. Sigue intentando."};
        int index = random.nextInt(negativeFeedback.length);
        System.out.println(negativeFeedback[index]);
    }

    public static void displayResult(double percentage) {
        System.out.printf("Has respondido correctamente al %.2f%% de las preguntas.%n", percentage);
    }
}