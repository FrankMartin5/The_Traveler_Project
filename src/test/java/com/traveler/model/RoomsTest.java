package com.traveler.model;

//import static com.traveler.model.Rooms.roomsArray;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
//import com.traveler.model.Rooms;
import org.junit.Test;

import java.util.*;

class RoomsTest {
    public static void main(String[] args) throws JsonProcessingException {

        //System.out.println(roomsArray);

        ObjectMapper mapper = new ObjectMapper();
//        Rooms[] room = mapper.readValue(roomsArray, Rooms[].class);
//
//
//
//            for (Rooms r : room) {
//                System.out.println(r.getName());
//                System.out.println("=========================");
//                System.out.println(r.getDesc());
//                System.out.println("=========================");
//                System.out.println(r.getSouth());
//                System.out.println("=========================");
//                System.out.println(r.getNorth());
//                System.out.println("=========================");
//                System.out.println(r.getEast());
//                System.out.println("=========================");
//                System.out.println(r.getWest());
//                System.out.println("=========================");
//
//
//            }
    }

}