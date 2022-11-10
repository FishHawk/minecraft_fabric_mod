package net.fabricmc.example.util;

//public static boolean isUsingCorrectToolForDrops(BlockState state, @Nullable BlockPos pos, Player player) {
//    return isUsingCorrectTool(state, pos, player, ModTags.Blocks.ALWAYS_DROPS, Config.INSTANCE.doBlocksDropWithoutCorrectTool, Config.INSTANCE.doInstantBreakBlocksDropWithoutCorrectTool);
//}
public final class Helpers {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }
}
