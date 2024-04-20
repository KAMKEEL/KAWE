package com.boydti.fawe.forge.v1710;

import com.boydti.fawe.object.FaweChunk;
import com.boydti.fawe.object.FaweQueue;
import com.boydti.fawe.wrappers.WorldWrapper;
import com.sk89q.worldedit.forge.ForgeWorld;
import com.sk89q.worldedit.world.AbstractChunkUpdater;
import com.sk89q.worldedit.world.World;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

import java.util.Collection;

public class ForgeChunkUpdater extends AbstractChunkUpdater {
    @Override
    public void updateChunks(World world, FaweQueue queue, Collection<FaweChunk> chunks) {
        if(world instanceof WorldWrapper)
            world = ((WorldWrapper) world).getParent();

        if(!(world instanceof ForgeWorld))
            return;

        net.minecraft.world.WorldServer worldMC = (WorldServer) ((ForgeWorld) world).getWorld();
        new Thread(() -> {
            for(Object p : worldMC.playerEntities){
                for(FaweChunk c : chunks){
                    Chunk chunk = (Chunk) c.getChunk();
                    EntityPlayerMP player = (EntityPlayerMP) p;
                    player.playerNetServerHandler.sendPacket(new S21PacketChunkData(chunk, true, 65535));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }
}
