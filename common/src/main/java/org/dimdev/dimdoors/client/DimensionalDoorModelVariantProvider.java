package org.dimdev.dimdoors.client;

//public class DimensionalDoorModelVariantProvider implements ModelVariantProvider { //TODO: Move to fabric if needed still
//	private static final Identifier childItem = DimensionalDoors.id("item/child_item");
//
//	@Override
//	public @Nullable UnbakedModel loadModelVariant(ModelIdentifier modelId, ModelProviderContext context) throws ModelProviderException {
//		Identifier identifier = new Identifier(modelId.getNamespace(), modelId.getPath());
//
//		DimensionalDoorBlockRegistrar blockRegistrar = DimensionalDoors.getDimensionalDoorBlockRegistrar();
//		if (blockRegistrar.isMapped(identifier)) {
//			Identifier mapped = blockRegistrar.get(identifier);
//			//ModelIdentifier newId = new ModelIdentifier(mapped, modelId.getVariant());
//			//UnbakedModel model = context.loadModel(newId);
//			//if (model != null) return model;
//
//			Block original = Registries.BLOCK.get(mapped);
//			Set<String> originalProperties = original.getStateManager().getProperties().stream().map(Property::getName).collect(Collectors.toSet());
//
//			ArrayList<String> variantArray = new ArrayList<>();
//			for (String part : modelId.getVariant().split(",")) {
//				if (originalProperties.contains(part.split("=")[0])) variantArray.add(part);
//			}
//			String variant = String.join(",", variantArray);
//			ModelIdentifier newId = new ModelIdentifier(mapped, variant);
//			return context.loadModel(newId);
//		} else if (identifier.getPath().startsWith(DimensionalDoorItemRegistrar.PREFIX)) {
//			return context.loadModel(childItem);
//		}
//		return null;
//	}
//}
