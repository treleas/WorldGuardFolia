package com.sk89q.worldguard.bukkit.util;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SchedulerUtils {
  private static boolean folia = false;
  static {
    try {
      Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
      folia = true;
    } catch (final ClassNotFoundException ignore) {}
  }

  public static @NotNull Object scheduleSyncRepeatingTask(
    final @NotNull Plugin plugin,
    final @NotNull Runnable task,
    final long delay,
    final long period
  ) {
    if (folia) {
      return Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, __ -> task.run(), delay, period);
    } else {
      return Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, delay, period);
    }
  }

  public static void runTaskLater(final @NotNull Plugin plugin, final @NotNull Runnable task, final long delay) {
    if (folia) {
      Bukkit.getGlobalRegionScheduler().runDelayed(plugin, __ -> task.run(), delay);
    } else {
      Bukkit.getScheduler().runTaskLater(plugin, task, delay);
    }
  }

  public static void runTask(final @NotNull Plugin plugin, final @NotNull Runnable task) {
    if (folia) {
      Bukkit.getGlobalRegionScheduler().run(plugin, __ -> task.run());
    } else {
      Bukkit.getScheduler().runTask(plugin, task);
    }
  }

  public static void cancelTasks(final @NotNull Plugin plugin) {
    if (folia) {
      Bukkit.getGlobalRegionScheduler().cancelTasks(plugin);
    } else {
      Bukkit.getScheduler().cancelTasks(plugin);
    }
  }

  public static void cancelTask(final @NotNull Plugin plugin, final @NotNull Object task) {
    if (folia) {
      ((ScheduledTask) task).cancel();
    } else {
      Bukkit.getScheduler().cancelTask((Integer) task);
    }
  }
}
