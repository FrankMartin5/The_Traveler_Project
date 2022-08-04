package com.traveler.view;

public class Text {
    public String intro = "==================== THE STORY SO FAR ====================" +
            "\nThe year is 3134. \nYou are Idryssa, an army Sergeant and you have just discovered " +
            "that your Ancestor Asbestos was a very powerful warlord who ruled all of Vrubosa " + "when he was cursed by the evil warlock Racumen. This curse has first born " +
            "male sons of your family die by the age of 35 and has followed your family for " +
            "generations. \n\nIt is the week of your 35th birthday and you and your hologram companion," +
            " Reanu Keeves must now travel back to the past to stop Racumen from placing a curse on your family.";

    public String help = "List of available commands: \nlook <item/direction>: get information\ngo <direction>: enter room in that direction" +
            "\nget <item>: adds item to inventory\nroom info: get information about the current room\n" +
            "talk <npc>: talk to the npc\nfight <npc>: starts combat if possible with npc\n" +
            "map: displays the game map\nquit game: exit the game without saving\nhelp: displays the available commands"
            + "\nstatus: player's status";

    public String newGame = "STARTING NEW GAME";

    public String savedGame = "STARTING SAVED GAME";

    public String gameOver = "GAME OVER";

    public String prompt = "\nWhat would you like to do? ";

    public String newGamePrompt = "Would you like to start a new game or continue from save? [N]ew game or [S]saved game: ";

    public String newGamePromptError = "Please enter valid response, n or s";

    public String askRiddle = "\nWhat is the answer? ";

    public String gameWin = "CONGRATS!!!!\n" +
            "You have defeated the Boss, you are a true gamer. ";
}