package com.example.android.amity;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

/**
 * Created by Marilyn on 4/19/2017.
 */

public class Cities {

    HashMap<String, int[]> cities;
    public void createHashMap() throws IOException
    {
        cities = new HashMap<String, int[]>();
        Scanner scan = new Scanner (new File("cities.txt"));
        while (scan.hasNextLine())
        {
            String cityName = scan.next();
            int[] longLat = {Integer.valueOf(scan.next()), Integer.valueOf(scan.next())};
            cities.put(cityName, longLat);
        }

    }

    public static void main (String[] args) throws IOException
    {

    }
}
