package lach_01298.qmd.recipe.ingredient;

import lach_01298.qmd.particle.ParticleStack;
import nc.recipe.*;
import nc.recipe.ingredient.IIngredient;

import java.util.List;

public interface IParticleIngredient extends IIngredient<ParticleStack>
{
	@Override
	public default ParticleStack getNextStack(int ingredientNumber)
	{
		ParticleStack nextStack = getStack();
		nextStack.setAmount(getNextStackSize(ingredientNumber));
		return nextStack;
	}

	@Override
	public default List<ParticleStack> getInputStackHashingList()
	{
		return getInputStackList();
	}

	@Override
	public IParticleIngredient getFactoredIngredient(int factor);
	
	
	public IngredientMatchResult match(Object object, IngredientSorption sorption);

	public IngredientMatchResult matchWithData(Object object, IngredientSorption type, List extras);
}
