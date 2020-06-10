package campominado;

import java.util.*;

/**
 * @author Gabriel.Camargo, Pedro.Cardoso, Miguel.Angelo
 *
 */
public class CampoMinado {

    public static void main(String[] args) {
        // Controls the game
        game();
    }

    /*
     * This function will execute the logic of this game, call all functions describes below and control the entire game,
     * for example, quantity of mines in the game and set the length of the board. For each play, the board minefield
     * must show the screen, remember it will only show the squares already discovered (Player's perspective). At the end of the game,
     * this function must show if player won or lost and show the board minefield.
     */
    
    public static void game() {
    	char[][] M = initialize(5, 5, 10);
        boolean gameControl = false, gameModeSelected = false;
        int lin, col, opt = 0, realBomb = 3, interfaceBomb = realBomb;
        Scanner read = new Scanner(System.in);

        //--Game's Name
        System.out.println("MINEFIELD O.P.");

        //--Main Loop
        while (!gameControl) {
            //-- Game's Selection Mode
            if (!gameModeSelected) {
                System.out.println("Select a Game Mode: \n[0] - Stantard Mode.\n[1] - 8x8 Mode.\n[2] - 20x20 Mode.\n[3] - 30x30 Mode.\n[4] - 90x90 Mode.");
                switch (read.nextInt()) {
                    case 0: //-- Standard / bombs: (x * y - 5 / 2)
                        M = initialize(5, 5, 10);
                        realBomb = 10;
                        break;
                        
                    case 1: //-- 8x8
                        M = initialize(8, 8, 30);
                        realBomb = 30;
                        break;
                        
                    case 2://-- 20x20
                        M = initialize(20, 20, 197);
                        realBomb = 197;
                        break;
                        
                    case 3://-- 30x30
                        M = initialize(30, 30, 447);
                        realBomb = 447;
                        break;
                        
                    case 4://-- 90x90
                        M = initialize(90, 90, 4000);
                        realBomb = 4000;
                        break;
                        
                    default://--Error
                        System.out.println("Invalid Mode");
                        break;
                }
                interfaceBomb = realBomb;
                gameModeSelected = true;
            }

            //--Initial Print
            print(M, interfaceBomb);

            //-- Menu
            System.out.println("Select an option: \n[0] - Open the position.\n[1] - Mark the position.\n[2] - Mark off the position.");
            opt = read.nextInt();

            //-- Coordinates
            System.out.println("Type the coordinates:");
            lin = read.nextInt();
            col = read.nextInt();

            //-- Second Loop (Verify  actions in the game)
            switch (step(M, lin, col, opt, interfaceBomb)) {
                case -1:
                    if (status(M, realBomb)) { //Victory condition
                        print(M, interfaceBomb);
                        System.out.println("Congrats, you Won!   \n                                       .....'',;;::cccllllllllllllcccc:::;;,,,''...'',,'..\n"
                                                                    + "                            ..';cldkO00KXNNNNXXXKK000OOkkkkkxxxxxddoooddddddxxxxkkkkOO0XXKx:.\n"
                                                                    + "                      .':ok0KXXXNXK0kxolc:;;,,,,,,,,,,,;;,,,''''''',,''..              .'lOXKd'\n"
                                                                    + "                 .,lx00Oxl:,'............''''''...................    ...,;;'.             .oKXd.\n"
                                                                    + "              .ckKKkc'...'',:::;,'.........'',;;::::;,'..........'',;;;,'.. .';;'.           'kNKc.\n"
                                                                    + "           .:kXXk:.    ..       ..................          .............,:c:'...;:'.         .dNNx.\n"
                                                                    + "          :0NKd,          .....''',,,,''..               ',...........',,,'',,::,...,,.        .dNNx.\n"
                                                                    + "         .xXd.         .:;'..         ..,'             .;,.               ...,,'';;'. ...       .oNNo\n"
                                                                    + "         .0K.         .;.              ;'              ';                      .'...'.           .oXX:\n"
                                                                    + "        .oNO.         .                 ,.              .     ..',::ccc:;,..     ..                lXX:\n"
                                                                    + "       .dNX:               ......       ;.                'cxOKK0OXWWWWWWWNX0kc.                    :KXd.\n"
                                                                    + "     .l0N0;             ;d0KKKKKXK0ko:...              .l0X0xc,...lXWWWWWWWWKO0Kx'                   ,ONKo.\n"
                                                                    + "   .lKNKl...'......'. .dXWN0kkk0NWWWWWN0o.            :KN0;.  .,cokXWWNNNNWNKkxONK: .,:c:.      .';;;;:lk0XXx;\n"
                                                                    + "  :KN0l';ll:'.         .,:lodxxkO00KXNWWWX000k.       oXNx;:okKX0kdl:::;'',;coxkkd, ...'. ...'''.......',:lxKO:.\n"
                                                                    + " oNNk,;c,'',.                      ...;xNNOc,.         ,d0X0xc,.     .dOd,           ..;dOKXK00000Ox:.   ..''dKO,\n"
                                                                    + "'KW0,:,.,:..,oxkkkdl;'.                'KK'              ..           .dXX0o:'....,:oOXNN0d;.'. ..,lOKd.   .. ;KXl.\n"
                                                                    + ";XNd,;  ;. l00kxoooxKXKx:..ld:         ;KK'                             .:dkO000000Okxl;.   c0;      :KK;   .  ;XXc\n"
                                                                    + "'XXdc.  :. ..    '' 'kNNNKKKk,      .,dKNO.                                   ....       .'c0NO'      :X0.  ,.  xN0.\n"
                                                                    + ".kNOc'  ,.      .00. ..''...      .l0X0d;.             'dOkxo;...                    .;okKXK0KNXx;.   .0X:  ,.  lNX'\n"
                                                                    + " ,KKdl  .c,    .dNK,            .;xXWKc.                .;:coOXO,,'.......       .,lx0XXOo;...oNWNXKk:.'KX;  '   dNX.\n"
                                                                    + "  :XXkc'....  .dNWXl        .';l0NXNKl.          ,lxkkkxo' .cK0.          ..;lx0XNX0xc.     ,0Nx'.','.kXo  .,  ,KNx.\n"
                                                                    + "   cXXd,,;:, .oXWNNKo'    .'..  .'.'dKk;        .cooollox;.xXXl     ..,cdOKXXX00NXc.      'oKWK'     ;k:  .l. ,0Nk.\n"
                                                                    + "    cXNx.  . ,KWX0NNNXOl'.           .o0Ooldk;            .:c;.':lxOKKK0xo:,.. ;XX:   .,lOXWWXd.      . .':,.lKXd.\n"
                                                                    + "     lXNo    cXWWWXooNWNXKko;'..       .lk0x;       ...,:ldk0KXNNOo:,..       ,OWNOxO0KXXNWNO,        ....'l0Xk,\n"
                                                                    + "     .dNK.   oNWWNo.cXK;;oOXNNXK0kxdolllllooooddxk00KKKK0kdoc:c0No        .'ckXWWWNXkc,;kNKl.          .,kXXk,\n"
                                                                    + "      'KXc  .dNWWX;.xNk.  .kNO::lodxkOXWN0OkxdlcxNKl,..        oN0'..,:ox0XNWWNNWXo.  ,ONO'           .o0Xk;\n"
                                                                    + "      .ONo    oNWWN0xXWK, .oNKc       .ONx.      ;X0.          .:XNKKNNWWWWNKkl;kNk. .cKXo.           .ON0;\n"
                                                                    + "      .xNd   cNWWWWWWWWKOkKNXxl:,'...;0Xo'.....'lXK;...',:lxk0KNWWWWNNKOd:..   lXKclON0:            .xNk.\n"
                                                                    + "      .dXd   ;XWWWWWWWWWWWWWWWWWWNNNNNWWNNNNNNNNNWWNNNNNNWWWWWNXKNNk;..        .dNWWXd.             cXO.\n"
                                                                    + "      .xXo   .ONWNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNK0ko:'..OXo          'l0NXx,              :KK,\n"
                                                                    + "      .OXc    :XNk0NWXKNWWWWWWWWWWWWWWWWWWWWWNNNX00NNx:'..       lXKc.     'lONN0l.              .oXK:\n"
                                                                    + "      .KX;    .dNKoON0;lXNkcld0NXo::cd0NNO:;,,'.. .0Xc            lXXo..'l0NNKd,.              .c0Nk,\n"
                                                                    + "      :XK.     .xNX0NKc.cXXl  ;KXl    .dN0.       .0No            .xNXOKNXOo,.               .l0Xk;.\n"
                                                                    + "     .dXk.      .lKWN0d::OWK;  lXXc    .OX:       .ONx.     . .,cdk0XNXOd;.   .'''....;c:'..;xKXx,\n"
                                                                    + "     .0No         .:dOKNNNWNKOxkXWXo:,,;ONk;,,,,,;c0NXOxxkO0XXNXKOdc,.  ..;::,...;lol;..:xKXOl.\n"
                                                                    + "     ,XX:             ..';cldxkOO0KKKXXXXXXXXXXKKKKK00Okxdol:;'..   .';::,..':llc,..'lkKXkc.\n"
                                                                    + "     :NX'    .     ''            ..................             .,;:;,',;ccc;'..'lkKX0d;.\n"
                                                                    + "     lNK.   .;      ,lc,.         ................        ..,,;;;;;;:::,....,lkKX0d:.\n"
                                                                    + "    .oN0.    .'.      .;ccc;,'....              ....'',;;;;;;;;;;'..   .;oOXX0d:.\n"
                                                                    + "    .dN0.      .;;,..       ....                ..''''''''....     .:dOKKko;.\n"
                                                                    + "     lNK'         ..,;::;;,'.........................           .;d0X0kc'.\n"
                                                                    + "     .xXO'                                                 .;oOK0x:.\n"
                                                                    + "      .cKKo.                                    .,:oxkkkxk0K0xc'.\n"
                                                                    + "        .oKKkc,.                         .';cok0XNNNX0Oxoc,.\n"
                                                                    + "          .;d0XX0kdlc:;,,,',,,;;:clodkO0KK0Okdl:,'..\n"
                                                                    + "              .,coxO0KXXXXXXXKK0OOxdoc:,..\n"
                                                                    + "                        ...\n SQN!");
                    }
                    gameControl = status(M, realBomb);
                    break;
                	
                case 0:
                    print(M, interfaceBomb);
                    System.out.println("               ...\n" +
                                        "             ;::::;\n" +
                                        "           ;::::; :;\n" +
                                        "         ;:::::'   :;\n" +
                                        "        ;:::::;     ;.\n" +
                                        "       ,:::::'       ;           OOO\\\n" +
                                        "       ::::::;       ;          OOOOO\\\n" +
                                        "       ;:::::;       ;         OOOOOOOO\n" +
                                        "      ,;::::::;     ;'         / OOOOOOO\n" +
                                        "    ;:::::::::`. ,,,;.        /  / DOOOOOO\n" +
                                        "  .';:::::::::::::::::;,     /  /     DOOOO\n" +
                                        " ,::::::;::::::;;;;::::;,   /  /        DOOO\n" +
                                        ";`::::::`'::::::;;;::::: ,#/  /          DOOO\n" +
                                        ":`:::::::`;::::::;;::: ;::#  /            DOOO\n" +
                                        "::`:::::::`;:::::::: ;::::# /              DOO\n" +
                                        "`:`:::::::`;:::::: ;::::::#/               DOO\n" +
                                        " :::`:::::::`;; ;:::::::::##                OO\n" +
                                        " ::::`:::::::`;::::::::;:::#                OO\n" +
                                        " `:::::`::::::::::::;'`:;::#                O\n" +
                                        "  `:::::`::::::::;' /  / `:#\n" +
                                        "   ::::::`:::::;'  /  /   `#\n" +
                                        "     _            _   _     \n" +
                                        "    | |          | | | |    \n" +
                                        "  __| | ___  __ _| |_| |__  \n" +
                                        " / _` |/ _ \\/ _` | __| '_ \\ \n" +
                                        "| (_| |  __/ (_| | |_| | | |\n" +
                                        " \\__,_|\\___|\\__,_|\\__|_| |_|\n" +
                                        "                            ");
                    gameControl = true;
                    break;
                case 1:
                    interfaceBomb--;
                    break;
                case 2:
                    interfaceBomb++;
                    break;
                case 3:
                    System.out.println("Invalid Position");
                    break;
                case 4:
                    System.out.println("This position include a flag, uncheck it before opening it.");
                    break;
            }
        }
    }

    /*
     * as we will use a two-dimensional array NxM to represent the board minefield, the
     * function initialize() must give an two-dimensional array with the mines already spread in the array, and in
     * other positions where don't have mines, you must check how many mines there has in the adjacent squares.
     * The definition of the quantity mines and the board length is at the discretion of the students,
     * as well as spreading the mines over the array. For the game to be challenging, try to spread random mines.
     */
    public static char[][] initialize(int width, int height, int amountBomb) {
        Random randomNum = new Random();
        int bombMaxAmount = amountBomb;

        char[][] M = new char[width + 2][height + 2];

        //Define two-dimensional array
        for (int l = 1; l < M.length - 1; l++) //Walk on the row
        {
            for (int c = 1; c < M[0].length - 1; c++) //Walk on the column
            {
                M[l][c] = '-';

            }
        }

        //Instantiate random bombs in the array(Warning: EM ALGUNS CASOS O NUMERO SORTEADO SE REPETE, SENDO ASSIM NUNCA SAIRA DO LOOP EM CASOS DE NUMEROS PERTO DE QUANTIA MAXIMA)
        while (bombMaxAmount > 0) //Walk on the row
        {
            //Generate a random number.
            int x = randomNum.nextInt(width + 1), y = randomNum.nextInt(height + 1);

            //Adjustment random X and Y.
            if (x == 0) {
                x++;
            }
            if (y == 0) {
                y++;
            }

            if (x == width + 1) {
                x--;
            }
            if (y == height + 1) {
                y--;
            }

            //Check to place a bomb
            if (M[x][y] != 'B') {
                M[x][y] = 'B';
                bombMaxAmount--;
            }

        }
        return M;
    }

    /*
     * This function prints the minefield, based on the player's vision based, on the console, don't forget 
     * the print must be as informative as possible, for the player to decide what to do.
     */
    public static void print(char[][] M, int bomb) {

        for (int l = 0; l < M.length - 1; l++) { //Walk on the row
            for (int c = 0; c < M[0].length - 1; c++) //Walk on the column
            {
                //-- Interface / Border
                if (l == 0 && c == 0) {
                    System.out.printf(" 0" + l + "|");
                } else if (c == 0) {
                    if (l > 9) {
                        System.out.printf(" " + l + "|");
                    } else {
                        System.out.printf(" 0" + l + "|");
                    }
                } else if (l == 0) {
                    if (c > 9) {
                        System.out.printf(" " + c + " ");
                    } else {
                        System.out.printf(" 0" + c + " ");
                    }
                } else {
                    //--Game conditions
                    switch (M[l][c]) {
                        case 'B'://Hide the bomb
                            System.out.printf("[" + "--" + "]");//â€¢ bomb
                            break;
                        case 'P'://Hide only the flag
                            System.out.printf("[" + "✅" + " ]");
                            break;
                        case 'E'://Hide bomb with flag
                            System.out.printf("[" + "✅" + "  ]");
                            break;
                        default:// Standard.
                            if (M[l][c] == '-') {
                                System.out.printf("[-" + M[l][c] + "]");
                            } else {
                                if (M[l][c] == '0') //Check if it is open, if yes but has no bomb, keep a void value.
                                {
                                    System.out.printf("[  ]");
                                } else {
                                    System.out.printf("[0" + M[l][c] + "]");
                                }
                            }
                            break;
                    }
                }
            }
            System.out.println(" ");
        }
        System.out.println("Bombas escondidas: " + bomb);
        System.out.println(" ");
    }

    /*
     This function receive the minefield, one position and one action to be made
    (mark or reveal position). If the reveal action has succeed return -1, and the game goes on,
    if reveal one bomb return 0 and the game is over, if mark one position, return 1, and the game continues.
    At the end, array is updated.
     */
    public static int step(char[][] M, int lin, int col, int whichOption, int bomb) {
        int value = -1, countB = '0', pivotStartX = lin - 1, pivotStartY = col - 1, pivotFinalX = pivotStartX + 2, pivotFinalY = pivotStartY + 2;
        if (lin > 0 && col > 0 && lin < M.length - 1 && col < M[0].length - 1) {
            switch (whichOption) {
                case 0:
                    if (M[lin][col] != 'P') {
                        if (M[lin][col] != 'B' && M[lin][col] != 'E') {
                            for (int l = pivotStartX; l <= pivotFinalX; l++) {
                                for (int c = pivotStartY; c <= pivotFinalY; c++) {
                                    // print(M);
                                    //M[l][c] = 'M';
                                    if (M[l][c] == 'B' || M[l][c] == 'E') {
                                        countB++;
                                    }
                                }
                            }
                            M[lin][col] = (char) countB;
                        } else {
                            value = 0;
                        }
                    } else {
                        value = 4;
                    }
                    break;

                case 1: // Mark position
                    if (bomb > 0) {
                        if (M[lin][col] != 'B' && M[lin][col] == '-') {
                            M[lin][col] = 'P';
                        } else if (M[lin][col] == 'B') {
                            M[lin][col] = 'E';
                        }
                        value = 1;
                    }
                    break;

                case 2://Mark off
                    if (M[lin][col] == 'P') {
                        M[lin][col] = '-';
                    } else if (M[lin][col] == 'E') {
                        M[lin][col] = 'B';
                    }
                    value = 2;
                    break;
            }
        } else {
            value = 3;
        }
        return value;
    }

    /*
    At the end, the function status() verify if the player find all mines around the game.
    If this condition exists, return true, else return false
     */
    public static boolean status(char[][] M, int b) {
        boolean verify = false;
        int value = 0, countM = 0;
        for (int l = 1; l < M.length - 1; l++) { //Walk on the row
            for (int c = 1; c < M[0].length - 1; c++) //Walk on the column
            	
            	
            {
                if (M[l][c] != '-' && M[l][c] != 'B') {
                    value++;
                }
                countM++;
            }
        }

        if (value == countM - b) {
            verify = true;
        }

        return verify;
    }

}
