package com.traveler.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

public class TauntsTest {
    Taunts taunts = new Taunts();


    @Test
    public void genOrcDialogueTest_shouldReturn_correctOrcDialogueStrings() {

        Assertions.assertEquals(Arrays.asList("\"You're CLEARLY a waste o' good food\"\n", "\"HAH, AWFUL GUESS!\"\n", "\"Are ALL humans as dim-witted as you?\"\n",
                "\"He just looks at you disappointingly...\"\n"), Taunts.genOrcDialogue());
    }

    @Test
    public void genTrollDialogue_shouldReturn_correctTrollDialogueStrings() {
        Assertions.assertEquals(Arrays.asList("\"WHY YOU SO DUMB?\" it slaps the ground in anger\n", "\"HEHEHE WROOOOONG\"\n", "\"BOOOOO HUMAN, BOOOOOO! YOU'RE VERY BAD AT THIS!\"\n",
                "\"The Troll gives you a thumbs down of disapproval. A massive thumbs down at that.\"\n"), Taunts.genTrollDialogue());
    }

    @Test
    public void genFbDialogue_shouldReturn_correctFinalBossDialogueStrings() {
        Assertions.assertEquals(Arrays.asList("\"Embarrassing.\"\n", "\"Is this the best you could muster against me after all this time?\"\n", "\"I'm surprised my earlier two minions didn't wipe you out first.\"\n",
                "\"Are you done yet?\"\n"), Taunts.genFbDialogue());
    }

    @RepeatedTest(10)
    public void confirmRandomOutputs_orcTaunts(RepetitionInfo repetitionInfo) {
        Taunts.orcTaunts();

        Assertions.assertEquals(10, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(10)
    public void confirmRandomOutputs_trollTaunts(RepetitionInfo repetitionInfo) {
        Taunts.trollTaunts();

        Assertions.assertEquals(10, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(10)
    public void confirmRandomOutputs_fbTaunts(RepetitionInfo repetitionInfo) {
        Taunts.fbTaunts();

        Assertions.assertEquals(10, repetitionInfo.getTotalRepetitions());
    }

}