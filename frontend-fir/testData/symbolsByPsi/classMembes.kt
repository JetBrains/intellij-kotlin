class A {
    val a: Int = 10
    fun x() = 10
}

// SYMBOLS:
/*
KtFirPropertySymbol:
  fqName: A.a
  isExtension: false
  isVal: true
  name: a
  origin: SOURCE
  receiverType: kotlin/Int
  symbolKind: MEMBER
  type: kotlin/Int

KtFirFunctionSymbol:
  fqName: A.x
  isExtension: false
  isOperator: false
  isSuspend: false
  name: x
  origin: SOURCE
  symbolKind: MEMBER
  type: kotlin/Int
  typeParameters: []
  valueParameters: []

KtFirClassOrObjectSymbol:
  classId: A
  classKind: CLASS
  name: A
  origin: SOURCE
  symbolKind: TOP_LEVEL
  typeParameters: []
*/
