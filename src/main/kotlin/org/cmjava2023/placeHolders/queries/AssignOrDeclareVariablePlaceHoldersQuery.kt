package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.astToClassFileData.AstTraverserToGetPlaceHolders
import org.cmjava2023.astToClassFileData.ValueNodeTransformedToTypeQuery
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

class AssignOrDeclareVariablePlaceHoldersQuery(
    private val astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders,
    private val loadConstantOperationQuery: LoadConstantOperationQuery
) {
    fun fetch(variableSymbol: Variable, expression: ASTNodes.Expression): List<PlaceHolder> {
        val typeOfVariable = variableSymbol.type

        val opCodesLoadingExpressionValueOnStack =
            if (expression is ASTNodes.ValueNode<*> && typeOfVariable is BuiltInType) {
                astTraverserToGetPlaceHolders.visit(ValueNodeTransformedToTypeQuery.fetch(expression, typeOfVariable))
            } else if (typeOfVariable is ArrayType && expression is ASTNodes.ArrayInstantiationWithValuesNode) {
                CreateArrayWithValuesPlaceHoldersQuery.fetch(loadConstantOperationQuery, expression, typeOfVariable, astTraverserToGetPlaceHolders)
            } else {
                astTraverserToGetPlaceHolders.dispatch(expression)
            }
        return opCodesLoadingExpressionValueOnStack
    }
}