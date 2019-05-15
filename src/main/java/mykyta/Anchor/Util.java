package mykyta.Anchor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import mykyta.Anchor.NMS.NMS;
import mykyta.Anchor.NMS.NMS_1_14_R1;

public class Util {
    public static Anchor instance;
    public static NMS nms;
    public static boolean debug = false;
    private final String af = "plugins" + File.separator + "Anchor" + File.separator + "anchors.db";

    public void setInstance(Anchor a) {instance = a;}

    public void setNMS() {            
        String version = "";
        try {version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];} 
        catch (ArrayIndexOutOfBoundsException e) { 
            //Bukkit.getServer().getConsoleSender().sendMessage(config.getString("messages.miscellaneous.prefix") + "Could not get server version. The plugin may not function correctly as a result.");
            if (debug) System.err.println(e);
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        //if (debug) Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.miscellaneous.prefix") + config.getString("messages.miscellaneous.running").replace("[version]", version)));

        /*if (version.equals("v1_7_R1")) {nms = new NMS_1_7_R1();}
        else if (version.equals("v1_8_R1")) {nms = new NMS_1_8_R1();}
        else if (version.equals("v1_9_R1")) {nms = new NMS_1_9_R1();}
        else if (version.equals("v1_9_R2")) {nms = new NMS_1_9_R2();}
        else if (version.equals("v1_10_R1")) {nms = new NMS_1_10_R1();}
        else if (version.equals("v1_11_R1")) {nms = new NMS_1_11_R1();}
        else if (version.equals("v1_12_R1")) {nms = new NMS_1_12_R1();}
        else if (version.equals("v1_13_R1")) {nms = new NMS_1_13_R1();}
        else if (version.equals("v1_13_R2")) {nms = new NMS_1_13_R2();}*/
        if (version.equals("v1_14_R1")) {nms = new NMS_1_14_R1();}
        else {
            //Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.miscellaneous.prefix") + "This version of Harbor is incompatible with your server version. As such, Harbor will be disabled."));
            Bukkit.getPluginManager().disablePlugin(instance);
        }
    }

    /**
     * Creates the Anchors storage database
     */
    public void createDatabase() {
        if (Files.exists(Paths.get(af))) return;
		
		try {
            Files.createDirectories(Paths.get(instance.getDataFolder().getAbsolutePath()));
            Connection c = DriverManager.getConnection("jdbc:sqlite:" + af);
            if (c == null) {System.out.println("An error occured while creating the database file."); return;}
            DatabaseMetaData m = c.getMetaData();
            System.out.println("The driver name is " + m.getDriverName());
            System.out.println("A new database has been created.");

            String q = "CREATE TABLE anchors (\n"
            + "	id INTEGER PRIMARY KEY,\n"
            + "	x INTEGER,\n"
            + "	y INTEGER,\n"
            + "	z INTEGER,\n"
            + "	expires INTEGER\n"
            + ");";

            Statement s = c.createStatement();
            s.execute(q);
        }
		catch (IOException | SQLException e) {
			e.printStackTrace();
        }
    }

    public Connection connect() {
        Connection c = null;
        try {c = DriverManager.getConnection("jdbc:sqlite:" + af);} 
        catch (SQLException e) {System.out.println(e.getMessage());}
        return c;
    }

    public void add(Block b) {
        try {		
            Connection c = this.connect();
            Statement s = c.createStatement();
            // TODO string formatting
            s.execute("INSERT INTO anchors(`x`,`y`,`z`,`expires`) VALUES (" + b.getX() + ", " + b.getY() + ", " + b.getZ() + ", " + System.currentTimeMillis() + ");");
            c.close();
        }
        catch(SQLException e) {/* TODO ERR*/}
    }

    public void remove(Block b) {
        try {		
            Connection c = this.connect();
            Statement s = c.createStatement();
            s.execute("DELETE FROM anchors WHERE `x`=" + b.getX() + " AND `y`=" + b.getY() + " AND `z`=" + b.getZ() + ";");
            c.close();
        }
        catch(SQLException e) {/* TODO ERR*/}
	}
}
