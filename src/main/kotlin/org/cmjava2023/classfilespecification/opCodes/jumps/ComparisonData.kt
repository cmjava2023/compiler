package org.cmjava2023.classfilespecification.opCodes.jumps

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classfilespecification.opCodes.OpCode

data class ComparisonData(val opCodesLoadingDataForComparison: List<OpCode>, val comparisonType: ComparisonType, val comparisonOperator: ASTNodes.ComparisonOperator)