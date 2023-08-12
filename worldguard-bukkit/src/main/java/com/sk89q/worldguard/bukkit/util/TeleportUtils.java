package com.sk89q.worldguard.bukkit.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TeleportUtils {
  private static boolean paper;
  static {
    try {
      Class.forName("io.papermc.paper.configuration.Configuration");
      paper = true;
    } catch (final ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static CompletableFuture<Boolean> teleport(final @NotNull Entity entity, final @NotNull Location location) {
    if (paper) {
      return entity.teleportAsync(location);
    } else {
      return CompletableFuture.completedFuture(entity.teleport(location));
    }
  }
}
