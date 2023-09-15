package live.nerotv.lobbysystem.manager;

import live.nerotv.lobbysystem.Main;
import live.nerotv.lobbysystem.api.PlayerAPI;
import live.nerotv.lobbysystem.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GUIManager {

    public static void fillInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, ItemManager.Placeholder);
        }
    }

    public static void openCompassInventory(Player p) {
        if(p.hasPermission("nero.team")) {
            if(p.isSneaking()) {
                Inventory CompassInventory = Bukkit.createInventory(null, 27, "§cTeam-Menü");
                fillInventory(CompassInventory);
                CompassInventory.setItem(10, ItemManager.createGuiItem(Material.ECHO_SHARD,"Creative-1"));
                CompassInventory.setItem(12, ItemManager.createGuiItem(Material.ECHO_SHARD,"SkyBlock-1"));
                CompassInventory.setItem(14, ItemManager.createGuiItem(Material.ECHO_SHARD,"SMP-1"));
                CompassInventory.setItem(16, ItemManager.createGuiItem(Material.ECHO_SHARD,"Stickfight-1"));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                p.openInventory(CompassInventory);
                return;
            }
        }
        Inventory CompassInventory = Bukkit.createInventory(null, 54, "§bNavigator");
        fillInventory(CompassInventory);
        if (!PlayerAPI.hasAnimations(p)) {
            CompassInventory.setItem(28, ItemManager.CompassItem01);
            CompassInventory.setItem(11, ItemManager.CompassItem02);
            CompassInventory.setItem(42, ItemManager.CompassItem03);
            CompassInventory.setItem(15, ItemManager.CompassItem04);
            CompassInventory.setItem(22, ItemManager.Lobby);
            CompassInventory.setItem(31, ItemManager.Maps);
            CompassInventory.setItem(19, ItemManager.CompassItem05);
            CompassInventory.setItem(34, ItemManager.CompassItem06);
            CompassInventory.setItem(38, ItemManager.CompassItem07);
            CompassInventory.setItem(25, ItemManager.jumper);
        } else {
            new Countdown(6, Main.get()) {
                @Override
                public void count(int time) {
                    if (p.getOpenInventory().getTopInventory() == CompassInventory) {
                        if (time == 5) {
                            CompassInventory.setItem(11, ItemManager.CompassItem02);
                            CompassInventory.setItem(15, ItemManager.CompassItem04);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 4) {
                            CompassInventory.setItem(19,ItemManager.CompassItem05);
                            CompassInventory.setItem(25,ItemManager.jumper);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 3) {
                            CompassInventory.setItem(28,ItemManager.CompassItem01);
                            CompassInventory.setItem(34,ItemManager.CompassItem06);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 2) {
                            CompassInventory.setItem(42,ItemManager.CompassItem03);
                            CompassInventory.setItem(38,ItemManager.CompassItem07);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 1) {
                            CompassInventory.setItem(31, ItemManager.Maps);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 0) {
                            CompassInventory.setItem(22, ItemManager.Lobby);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        }
                    }
                }
            }.startTicks();
        }
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(CompassInventory);
    }

    public static void openSettingsInventory(Player p) {
        Inventory settingsInventory = Bukkit.createInventory(null, InventoryType.ENDER_CHEST, "§bEinstellungen");
        fillInventory(settingsInventory);
        if (!PlayerAPI.hasAnimations(p)) {
            settingsInventory.setItem(13, ItemManager.Placeholder);
            settingsInventory.setItem(12, ItemManager.rainItem(p));
            settingsInventory.setItem(14, ItemManager.ScoreboardItem(p));
            settingsInventory.setItem(11, ItemManager.timeItem(p));
            settingsInventory.setItem(15, ItemManager.FlyItem(p));
            settingsInventory.setItem(10, ItemManager.AnimateItem(p));
            settingsInventory.setItem(16, ItemManager.BackPS);
        } else {
            new Countdown(4, Main.get()) {
                @Override
                public void count(int time) {
                    if (p.getOpenInventory().getTopInventory() == settingsInventory) {
                        if (time == 3) {
                            settingsInventory.setItem(13, ItemManager.Placeholder);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 2) {
                            settingsInventory.setItem(12, ItemManager.rainItem(p));
                            settingsInventory.setItem(14, ItemManager.ScoreboardItem(p));
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 1) {
                            settingsInventory.setItem(11, ItemManager.timeItem(p));
                            settingsInventory.setItem(15, ItemManager.FlyItem(p));
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 0) {
                            settingsInventory.setItem(10, ItemManager.AnimateItem(p));
                            settingsInventory.setItem(16, ItemManager.BackPS);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        }
                    }
                }
            }.startTicks();
        }
        p.openInventory(settingsInventory);
    }

    public static void openMapsInventory(Player p) {
        Inventory MapsInventory = Bukkit.createInventory(null, 3 * 9, "§bZyneon-Karten");
        MapsInventory.setItem(18, ItemManager.Placeholder);
        MapsInventory.setItem(19, ItemManager.Placeholder);
        MapsInventory.setItem(20, ItemManager.Placeholder);
        MapsInventory.setItem(21, ItemManager.Placeholder);
        MapsInventory.setItem(22, ItemManager.Lobby);
        MapsInventory.setItem(23, ItemManager.Placeholder);
        MapsInventory.setItem(24, ItemManager.Placeholder);
        MapsInventory.setItem(25, ItemManager.Placeholder);
        MapsInventory.setItem(26, ItemManager.Placeholder);
        if (!PlayerAPI.hasAnimations(p)) {
            MapsInventory.setItem(7, ItemManager.Deadwood1);
            MapsInventory.setItem(6, ItemManager.Argria1);
            MapsInventory.setItem(5, ItemManager.Primal3_1);
            MapsInventory.setItem(4, ItemManager.Primal3_2);
            MapsInventory.setItem(3, ItemManager.Primal3_3);
            MapsInventory.setItem(2, ItemManager.Shervann1);
            MapsInventory.setItem(1, ItemManager.Primal2_2);
            MapsInventory.setItem(0, ItemManager.Primal2_1);
        } else {
            new Countdown(13, Main.get()) {
                @Override
                public void count(int time) {
                    if (p.getOpenInventory().getTopInventory() == MapsInventory) {
                        if (time == 12) {
                            MapsInventory.setItem(0, ItemManager.Primal2_1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 11) {
                            MapsInventory.setItem(1, ItemManager.Primal2_2);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 10) {
                            MapsInventory.setItem(2, ItemManager.Shervann1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 9) {
                            MapsInventory.setItem(3, ItemManager.Primal3_3);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 8) {
                            MapsInventory.setItem(4, ItemManager.Primal3_2);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 7) {
                            MapsInventory.setItem(5, ItemManager.Primal3_1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 6) {
                            MapsInventory.setItem(6, ItemManager.Argria1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        } else if (time == 5) {
                            MapsInventory.setItem(7, ItemManager.Deadwood1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                        }
                    }
                }
            }.startTicks();
        }
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(MapsInventory);
    }

    public static Inventory confirmStopInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§fWillst du den Server §cstoppen§f?");
    public static void openConfirmStopInventory(Player p) {
        confirmStopInventory.setItem(0,ItemManager.Placeholder);
        confirmStopInventory.setItem(1,ItemManager.Cancel);
        confirmStopInventory.setItem(2,ItemManager.Placeholder);
        confirmStopInventory.setItem(3,ItemManager.StopYes);
        confirmStopInventory.setItem(4,ItemManager.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(confirmStopInventory);
    }

    public static Inventory confirmReloadInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§fWillst du den Server §creloaden§f?");
    public static void openConfirmReloadInventory(Player p) {
        confirmReloadInventory.setItem(0,ItemManager.Placeholder);
        confirmReloadInventory.setItem(1,ItemManager.Cancel);
        confirmReloadInventory.setItem(2,ItemManager.Placeholder);
        confirmReloadInventory.setItem(3,ItemManager.ReloadYes);
        confirmReloadInventory.setItem(4,ItemManager.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        p.openInventory(confirmReloadInventory);
    }

    public static void openProfileOptionsInventory(Player p) {
        Inventory profileOptionsInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§bProfil und Optionen§8");
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        fillInventory(profileOptionsInventory);
        p.openInventory(profileOptionsInventory);
        if (!PlayerAPI.hasAnimations(p)) {
            profileOptionsInventory.setItem(1, ItemManager.Profile(p));
            profileOptionsInventory.setItem(3, ItemManager.Settings);
        } else {
            new Countdown(1, Main.get()) {
                @Override
                public void count(int time) {
                    if (time == 0) {
                        profileOptionsInventory.setItem(1, ItemManager.Profile(p));
                        profileOptionsInventory.setItem(3, ItemManager.Settings);
                        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                    }
                }
            }.startTicks();
        }
    }

    public static void openMiniMusicInventory(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        Inventory extrasInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§bExtras");
        fillInventory(extrasInventory);
        p.openInventory(extrasInventory);
        if (!PlayerAPI.hasAnimations(p)) {
            extrasInventory.setItem(1, ItemManager.Minigames);
            extrasInventory.setItem(2, ItemManager.Extras);
            extrasInventory.setItem(3, ItemManager.Music);
        } else {
            new Countdown(2, Main.get()) {
                @Override
                public void count(int time) {
                    if (time == 1) {
                        extrasInventory.setItem(2, ItemManager.Extras);
                        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                    } else if (time == 0) {
                        extrasInventory.setItem(1, ItemManager.Minigames);
                        extrasInventory.setItem(3, ItemManager.Music);
                        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                    }
                }
            }.startTicks();
        }
    }

    public static void openExtrasInventory(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
        Inventory extrasInventory = Bukkit.createInventory(null,InventoryType.HOPPER,"§bExtras");
        fillInventory(extrasInventory);
        p.openInventory(extrasInventory);
        if (!PlayerAPI.hasAnimations(p)) {
            extrasInventory.setItem(2, ItemManager.Gadgets);
            extrasInventory.setItem(1, ItemManager.Cosmetics);
            extrasInventory.setItem(3, ItemManager.BackPS);
        } else {
            new Countdown(2, Main.get()) {
                @Override
                public void count(int time) {

                    if (time == 1) {
                        extrasInventory.setItem(2, ItemManager.Gadgets);
                        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                    } else if (time == 0) {
                        extrasInventory.setItem(1, ItemManager.Cosmetics);
                        extrasInventory.setItem(3, ItemManager.BackPS);
                        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 255);
                    }

                }
            }.startTicks();
        }
    }

    public static void openBackToLobbyInventory(Player p) {
        Inventory backToLobbyInventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§9Zurück zur Lobby?");
        backToLobbyInventory.setItem(0, ItemManager.Placeholder);
        backToLobbyInventory.setItem(1, ItemManager.Placeholder);
        backToLobbyInventory.setItem(2, ItemManager.BackTL);
        backToLobbyInventory.setItem(3, ItemManager.Placeholder);
        backToLobbyInventory.setItem(4, ItemManager.Placeholder);
        p.openInventory(backToLobbyInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 100);
    }
}