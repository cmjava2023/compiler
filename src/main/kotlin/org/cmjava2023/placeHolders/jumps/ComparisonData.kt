package org.cmjava2023.placeHolders.jumps

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classfilespecification.Operation

data class ComparisonData(val opCodesLoadingDataForComparison: List<Operation>, val comparisonType: ComparisonType, val comparisonOperator: ASTNodes.ComparisonOperator)