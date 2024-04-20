package com.sk89q.worldedit.world;

import com.boydti.fawe.object.FaweChunk;
import com.boydti.fawe.object.FaweQueue;

import java.util.Collection;

public abstract class AbstractChunkUpdater {

    public abstract void updateChunks(World world, FaweQueue queue, Collection<FaweChunk> chunks);

}
