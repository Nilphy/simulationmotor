package com.wesimulated.simulationmotor.operationbased;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.testng.collections.CollectionUtils;

import com.wesimulated.simulation.BaseExcecutor;
import com.wesimulated.simulation.runparameters.EndCodition;

public abstract class OperationBasedExecutor extends BaseExcecutor {
	private Collection<BOperationCore> bOperations;
	private List<COperationCore> cOperations;
	
	public OperationBasedExecutor(EndCodition endCondition) {
		super(endCondition);
	}
	
	protected void execAPhase() {
		if (bOperations.iterator().hasNext()) {
			this.getClock().advanceUntil(bOperations.iterator().next().getStartTime());
		}
	}
	
	protected void execBPhase() {
		Collection<BOperationCore> operationsToRemove = new ArrayList<>();
		for (BOperationCore operation : this.bOperations) {
			if (this.getClock().hasThisDate(operation.getStartTime())) {
				operation.doAction();
				operationsToRemove.add(operation);
			}
		}
		if (CollectionUtils.hasElements(operationsToRemove)) {
			for (BOperationCore operation : operationsToRemove) {
				this.bOperations.remove(operation);
			}
		}
	}

	protected void execCPhase() {
		Collection<COperationCore> operationsToRemove = new ArrayList<>();
		Boolean somethingHasChanged = false;
		orderCOperationsBeforeTestThemForExcecution();
		for (COperationCore cOperation : cOperations) {
			if (cOperation.testIfRequirementsAreMet()) {
				cOperation.doAction();
				operationsToRemove.add(cOperation);
			}
		}
		for (COperationCore cOperation : operationsToRemove) {
			cOperations.remove(cOperation);
			somethingHasChanged = true;
		}
		if (somethingHasChanged) {
			this.execCPhase(); // Retry again in case other operation can be made
		}
	}
	
	private void orderCOperationsBeforeTestThemForExcecution() {
		this.cOperations.sort(Comparator.comparing(COperationCore::getNegativePriority));
	}
	
	public void addBOperation(BOperation bOperation) {
		this.bOperations.add(new BOperationCore(bOperation));
	}
	
	@SuppressWarnings("unchecked")
	public void addAllBOperations(Collection<BOperation> bOperations) {
		this.bOperations.addAll((Collection<BOperationCore>) bOperations.stream().map(bOperation -> { return new BOperationCore(bOperation); }));
	}

	public void addCOperation(COperation cOperation) {
		this.cOperations.add(new COperationCore(cOperation));
	}
	
	@SuppressWarnings("unchecked")
	public void addAllCOperations(Collection<COperation> cOperations) {
		this.cOperations.addAll((Collection<COperationCore>) cOperations.stream().map(cOperation -> { return new COperationCore(cOperation); }));
	}

	protected void setCOperations(List<COperationCore> emptyCollectionOfCOperations) {
		this.cOperations = emptyCollectionOfCOperations;
	}
	
	protected void setBOperations(Collection<BOperationCore> emptyCollectionOfBOperations) {
		this.bOperations = emptyCollectionOfBOperations;
	}
}
