package com.traveler.model;

import java.util.*;

class Items {
    private HashMap<String, String> item = new HashMap<>();
    public static List<HashMap<String, String>> allItems = new ArrayList<HashMap<String, String>>();

    public void setItem(String a, String b) {
        item.put(a,b);
        allItems.add(item);
    }

    Map test = new Hashtable<String, String>();

    public static void getAllItems(){
        for (HashMap<String, String> item : allItems
             ) {
            System.out.println("PRINTING " + item);
        }
    }

    public static void itemLookUp(String noun) {
        for (HashMap<String, String> itemNoun : allItems
        ) {
            if( itemNoun.containsKey(noun)){
                System.out.println(noun+": " + itemNoun.get(noun));
            }else{
                System.out.println(noun+": not available.");
            }
        }
    }
    public static void main(String[] args) {
        Items i1 = new Items();
        i1.setItem("key", "skeleton key, the serrated edge has been removed to allow the key to serve as a master key to open many different warded locks within a specific system. Skeleton keys can work with warded locks or lever locks.\n" +

                "With a warded lock, a skeleton key lacks interior notches to interfere with or correspond with the wards, or obstructions, thereby allowing it to open the lock.\n" +

                "In a lever lock—that is, a lock with a set of levers and wards—the skeleton key can push the levers to the correct height while bypassing the wards. In this type of system, each lock will still have its own key that corresponds with the wards; the skeleton key, or master key, can open any of the locks within that system. (You may find these locks at hotels, office buildings, schools, or apartment buildings.)");

        Items i2 = new Items();
        i2.setItem("gun", "The Cabot Apocalypse is built around a deeply-etched stainless steel Damascus slide that is as durable as it is striking. Cabot’s proprietary Heritage Finish brings out the intricacies of the aggressively patterned steel while shielding the parts with a rock-hard exterior. Front and rear angled cocking serrations further highlight the dazzling surfaces. Like a fingerprint, each slide’s pattern is unique.\n" +

                "Despite the gun’s martial spirit, the fit and patinaed finish are as flawless as customers have come to expect from Cabot. The enhancements don’t stop there. Reflex optics are becoming the rule rather than the exception on serious defensive handguns. To facilitate such a pairing, Cabot engineered its own optional mount that sits low on the slide and co-witnesses the dot with the iron sights. As your eyes align with the sights, the illuminated dot becomes unmistakable. The speed and precision advantages are real.\n" +

                "The Apocalypse is available in several configurations including full-size and commander-length guns and comes standard with the new Cabot Ledge rear sight.  Our proprietary red dot mount for the Trijicon RMR/SRO is available with lower 1/3 co-witness sights (serrated front and rear).  It can be chambered in either .45 ACP or 9x19mm, with the option of a threaded barrel and tall sights for suppressor use. An integral frame-mounted light rail is standard but can be omitted.\n" +

                "Cabot’s Apocalypse doesn’t give up an inch in style or substance. This is a serious fighting iron with a breathtaking appearance.");

        Items i3 = new Items();
        i3.setItem("shield", "as stated in the Iliad, is a device carried by Athena and Zeus, variously interpreted as an animal skin or a shield and sometimes featuring the head of a Gorgon. There may be a connection with a deity named Aex or Aix, a daughter of Helios and a nurse of Zeus or alternatively a mistress of Zeus");
//        Items.getAllItems();
        Items.itemLookUp("key");
    }
}