package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.astToClassFileModel.AstTraverserToGetPlaceHolders
import org.cmjava2023.astToClassFileModel.TypeOfExpressionQuery
import org.cmjava2023.astToClassFileModel.ValueNodeTransformedToTypeQuery
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

class AssignOrDeclareVariablePlaceHoldersQuery {
    companion object {
        fun fetch(variableSymbol: Variable, expression: ASTNodes.Expression, astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders): List<PlaceHolder> {
            val typeOfVariable = variableSymbol.type
            val typeOfValueNode = TypeOfExpressionQuery.fetch(expression)

            val opCodesLoadingExpressionValueOnStack =
                if (expression is ASTNodes.ValueNode<*> && typeOfVariable.name != typeOfValueNode.name && typeOfVariable is BuiltInType) {
                    astTraverserToGetPlaceHolders.visit(ValueNodeTransformedToTypeQuery.fetch(expression, typeOfVariable))
                } else if (typeOfVariable is ArrayType && expression is ASTNodes.ArrayInstantiationWithValuesNode) {
                    astTraverserToGetPlaceHolders.visit(expression, typeOfVariable)
                } else {
                    astTraverserToGetPlaceHolders.dispatch(expression)
                }
            val storingOpCode = LocalVariableIndexPlaceHolder.createToStoreVariable(variableSymbol)
            return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
        }
    }
}