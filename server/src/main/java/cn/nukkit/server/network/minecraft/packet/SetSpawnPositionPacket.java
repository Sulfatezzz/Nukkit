package cn.nukkit.server.network.minecraft.packet;

import cn.nukkit.server.network.NetworkPacketHandler;
import cn.nukkit.server.network.minecraft.MinecraftPacket;
import com.flowpowered.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import static cn.nukkit.server.nbt.util.VarInt.writeSignedInt;
import static cn.nukkit.server.network.minecraft.MinecraftUtil.writeBlockPosition;

@Data
public class SetSpawnPositionPacket implements MinecraftPacket {
    private Type spawnType;
    private Vector3i blockPosition;
    private boolean spawnForced;

    @Override
    public void encode(ByteBuf buffer) {
        writeSignedInt(buffer, spawnType.ordinal());
        writeBlockPosition(buffer, blockPosition);
        buffer.writeBoolean(spawnForced);
    }

    @Override
    public void decode(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(NetworkPacketHandler handler) {
        // Only client bound.
    }

    public enum Type {
        PLAYER_SPAWN,
        WORLD_SPAWN
    }
}