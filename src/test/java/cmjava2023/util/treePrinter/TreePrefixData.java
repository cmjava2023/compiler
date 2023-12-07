package cmjava2023.util.treePrinter;

public class TreePrefixData {
    private static final String lastNodeOnLevelPrefix = "L  ";
    private static final String childrenPrefixWhenNodesFollowOnLevel = "|- ";
    private static final String childrenPrefixForLastNodeOnLevel = "   ";
    private static final String prefixToFill = "|  ";
    private final String prefix;
    private final String childrenPrefix;

    TreePrefixData() {
        prefix = "";
        childrenPrefix = "";
    }

    private TreePrefixData(String prefix, String childrenPrefix) {
        this.prefix = prefix;
        this.childrenPrefix = childrenPrefix;
    }

    public TreePrefixData createForNextLevel() {
        return new TreePrefixData(
            childrenPrefix + childrenPrefixWhenNodesFollowOnLevel,
            childrenPrefix + prefixToFill
        );
    }

    public TreePrefixData createForLastElementOfNextLevel() {
        return new TreePrefixData(
            childrenPrefix + lastNodeOnLevelPrefix,
            childrenPrefix + childrenPrefixForLastNodeOnLevel
        );
    }

    public StringBuilder getStringBuilderStartingWithPrefix() {
        return new StringBuilder(prefix);
    }

    public void appendPrefixToBuilder(StringBuilder stringBuilder) {
        stringBuilder.append(prefix);
    }
}
