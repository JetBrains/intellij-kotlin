fun foo(x: Int) {}

// RESULT
/*
KtFirValueParameterSymbol:
  annotatedType: [] kotlin/Int
  annotationClassIds: []
  annotations: []
  callableIdIfNonLocal: null
  hasDefaultValue: false
  isExtension: false
  isVararg: false
  name: x
  origin: SOURCE
  receiverType: null
  symbolKind: LOCAL

KtFirFunctionSymbol:
  annotatedType: [] kotlin/Unit
  annotationClassIds: []
  annotations: []
  callableIdIfNonLocal: /foo
  dispatchType: null
  isExtension: false
  isExternal: false
  isInline: false
  isOperator: false
  isOverride: false
  isSuspend: false
  modality: FINAL
  name: foo
  origin: SOURCE
  receiverType: null
  symbolKind: TOP_LEVEL
  typeParameters: []
  valueParameters: [KtFirValueParameterSymbol(x)]
  visibility: Public
*/
