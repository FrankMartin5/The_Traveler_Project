package com.traveler.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
public class TravelerAppTest {

    TravelerApp travelerApp;

    @Before
    public void setUp() {
        travelerApp = new TravelerApp();
    }

    @Test
    public void generateDrops_should_return_correct_items_description() {
        travelerApp.generateDrops();
        assertEquals("Pocket lint. Why do they even have this?", travelerApp.enemyDrops.get("lint"));
        assertEquals("Small metallic coins that looks to be some sort of currency.", travelerApp.enemyDrops.get("coins"));
        assertEquals("A ghost-like ball of swirling energy that seems to evade your capture no matter how much you try to hold it", travelerApp.enemyDrops.get("essence"));
        assertEquals("A crumpled piece of parchment with unfamiliar text scrawled onto it", travelerApp.enemyDrops.get("parchment"));
    }

    @Test
    public void levelUp_should_return_current_level() {
        travelerApp.player.setLvl(1);
        travelerApp.player.setExp(30);
        travelerApp.levelUp();
        assertEquals(2, travelerApp.player.getLvl());
    }

    @Test
    public void awardXP_should_add_exp_correctly_to_player_exp() {
        travelerApp.player.setExp(30);
        int newExp = travelerApp.player.getExp();
        assertEquals(newExp, travelerApp.player.getExp());
    }

    @Test
    public void generatePlayerFromJson_should_generate_based_on_json() {
        travelerApp.generatePlayerFromJson();
        assertEquals("Idryssa", travelerApp.player.getName());
        assertEquals(100, travelerApp.player.getHealth());
        assertEquals(0, travelerApp.player.getExp());
        assertEquals(1, travelerApp.player.getLvl());
        assertEquals(0, travelerApp.player.getInventory().size());
    }

    @Test
    public void playerStat() {
        travelerApp.generatePlayerFromJson();
        travelerApp.playerStat(travelerApp.player);
        assertEquals("Idryssa", travelerApp.player.getName());
        assertEquals(100, travelerApp.player.getHealth());
        assertEquals(0, travelerApp.player.getExp());
        assertEquals(1, travelerApp.player.getLvl());
        assertEquals(0, travelerApp.player.getInventory().size());
    }

}