package com.traveler.controller;

import junit.framework.TestCase;
import org.junit.*;

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
        Assert.assertEquals("Pocket lint. Why do they even have this?", travelerApp.enemyDrops.get("lint"));
        Assert.assertEquals("Small metallic coins that looks to be some sort of currency.", travelerApp.enemyDrops.get("coins"));
        Assert.assertEquals("A ghost-like ball of swirling energy that seems to evade your capture no matter how much you try to hold it", travelerApp.enemyDrops.get("essence"));
        Assert.assertEquals("A crumpled piece of parchment with unfamiliar text scrawled onto it", travelerApp.enemyDrops.get("parchment"));
    }

    @Test
    public void levelUp_should_return_current_level() {
        travelerApp.player.setLvl(1);
        travelerApp.player.setExp(30);
        travelerApp.levelUp();
        Assert.assertEquals(2, travelerApp.player.getLvl());
    }

    @Test
    public void awardXP_should_add_exp_correctly_to_player_exp() {
        travelerApp.player.setExp(30);
        int newExp = travelerApp.player.getExp();
        Assert.assertEquals(newExp, travelerApp.player.getExp());
    }

    @Test
    public void generatePlayerFromJson_should_generate_based_on_json() {
        travelerApp.generatePlayerFromJson();
        Assert.assertEquals("Idryssa", travelerApp.player.getName());
        Assert.assertEquals(100, travelerApp.player.getHealth());
        Assert.assertEquals(0, travelerApp.player.getExp());
        Assert.assertEquals(1, travelerApp.player.getLvl());
        Assert.assertEquals(0, travelerApp.player.getInventory().size());
    }

    @Test
    public void playerStat() {
        travelerApp.generatePlayerFromJson();
        travelerApp.playerStat(travelerApp.player);
        Assert.assertEquals("Idryssa", travelerApp.player.getName());
        Assert.assertEquals(100, travelerApp.player.getHealth());
        Assert.assertEquals(0, travelerApp.player.getExp());
        Assert.assertEquals(1, travelerApp.player.getLvl());
        Assert.assertEquals(0, travelerApp.player.getInventory().size());
    }

}