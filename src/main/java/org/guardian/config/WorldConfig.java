package org.guardian.config;

import java.util.ArrayList;
import org.bukkit.configuration.ConfigurationSection;

public class WorldConfig {

    private boolean blockBreak, blockBurn, blockDispense, blockFade, blockForm, blockFromTo,
            blockLavaFlow, blockLavaFlowAsPlayer, blockWaterFlow, blockWaterFlowAsPlayer, blockIgnite,
            blockPhysics, blockPiston, blockPlace, blockSpread, blockSignChange;
    private boolean entityEndermanPickup, entityEndermanPlace, entityDeath, entityCreeperExplode,
            entityCreeperExplodeAsPlayer, entityEnderdragonDestroy, entityGhastFireball,
            entityOtherExplode, entityTntExplode, entityPaintingBreak, entityPaintingPlace;
    private boolean playerChat, playerCommand, playerBedEnter, playerBedExit, playerBucketEmpty,
            playerBucketFill, playerDeath, playerItemDrop, playerInteract, playerJoin, playerKick,
            playerItemPickup, playerTeleport;
    private boolean vehicleCreate, vehicleDestroy, vehicleEnter, vehicleExit;
    private boolean worldPortalCreate, worldStructureGrow;
    private ArrayList<String> ignoredPlayers = new ArrayList<String>();

    public WorldConfig(final ConfigurationSection config) {
        // Block events
        blockBreak = config.getBoolean("block-break");
        blockBurn = config.getBoolean("block-burn");
        blockDispense = config.getBoolean("block-dispense");
        blockFade = config.getBoolean("block-fade");
        blockForm = config.getBoolean("block-form");
        blockFromTo = config.getBoolean("block-from-to");
        blockLavaFlow = config.getBoolean("block-lava-flow");
        blockLavaFlowAsPlayer = config.getBoolean("block-lava-flow-as-player");
        blockWaterFlow = config.getBoolean("block-water-flow-as-player");
        blockWaterFlowAsPlayer = config.getBoolean("block-water-flow-as-player");
        blockIgnite = config.getBoolean("block-ignite");
        blockPhysics = config.getBoolean("block-physics");
        blockPiston = config.getBoolean("block-piston");
        blockPlace = config.getBoolean("block-place");
        blockSpread = config.getBoolean("block-spread");
        blockSignChange = config.getBoolean("block-sign-change");
        // Entity events
        entityEndermanPickup = config.getBoolean("entity-endermen-pickup");
        entityEndermanPlace = config.getBoolean("entity-endermen-place");
        entityDeath = config.getBoolean("entity-death");
        entityCreeperExplode = config.getBoolean("entity-creeper-explode");
        entityCreeperExplodeAsPlayer = config.getBoolean("entity-creeper-explode-as-player");
        entityEnderdragonDestroy = config.getBoolean("entity-enderdragon-destroy");
        entityGhastFireball = config.getBoolean("entity-ghast-fireball");
        entityOtherExplode = config.getBoolean("entity-other-explode");
        entityTntExplode = config.getBoolean("entity-tnt-explode");
        entityPaintingBreak = config.getBoolean("entity-painting-break");
        entityPaintingPlace = config.getBoolean("entity-painting-place");
        // Player events
        playerChat = config.getBoolean("player-chat");
        playerCommand = config.getBoolean("player-command");
        playerBedEnter = config.getBoolean("player-bed-enter");
        playerBedExit = config.getBoolean("player-bed-exit");
        playerBucketEmpty = config.getBoolean("player-bucket-empty");
        playerBucketFill = config.getBoolean("player-bucket-fill");
        playerDeath = config.getBoolean("player-death");
        playerItemDrop = config.getBoolean("player-item-drop");
        playerInteract = config.getBoolean("player-interact");
        playerJoin = config.getBoolean("player-join");
        playerKick = config.getBoolean("player-kick");
        playerItemPickup = config.getBoolean("player-item-pickup");
        playerTeleport = config.getBoolean("player-teleport");
        // vehicle events
        vehicleCreate = config.getBoolean("vehicle-create");
        vehicleDestroy = config.getBoolean("vehicle-destroy");
        vehicleEnter = config.getBoolean("vehicle-enter");
        vehicleExit = config.getBoolean("vehicle-exit");
        // world events
        worldPortalCreate = config.getBoolean("world-portal-create");
        worldStructureGrow = config.getBoolean("world-structure-grow");
    }
}
