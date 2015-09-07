package com.wesimulated.simulationmotor.operationbased;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.wesimulated.simulation.BaseExcecutor;
import com.wesimulated.simulation.Clock;
import com.wesimulated.simulation.runparameters.EndCodition;

public abstract class Excecutor extends BaseExcecutor {
	private Collection<BOperation> bOperations;
	private List<COperation> cOperations;
	
	public Excecutor(EndCodition endCondition) {
		super(endCondition);
	}
	
	protected void execAPhase() {
		BOperation firstOperation = bOperations.iterator().next();
		Clock.getInstance().advanceUntil(firstOperation.getStartTime());
	}
	
	protected void execBPhase() {
		BOperation operationToRemove = null;
		for (BOperation bOperation : bOperations) {
			int comparisonResult = bOperation.getStartTime().compareTo(Clock.getInstance().getCurrentDate());
			if (comparisonResult == 0) {
				bOperation.doAction();
				operationToRemove = bOperation;
				break;
			} else if (comparisonResult > 0) {
				break;
			}
		}
		if (operationToRemove != null) {
			this.bOperations.remove(operationToRemove);
			this.execBPhase(); // retry all operations because maybe one that was previous on the list now can be done
		}
	}

	protected void execCPhase() {
		Collection<COperation> operationsToRemove = new ArrayList<>();
		orderCOperationsBeforeTestThemForExcecution();
		for (COperation cOperation : cOperations) {
			if (cOperation.testIfRequirementsAreMet()) {
				cOperation.doAction();
				operationsToRemove.add(cOperation);
			}
		}
		for (COperation cOperation : operationsToRemove) {
			cOperations.remove(cOperation);
		}
	}
	
	private void orderCOperationsBeforeTestThemForExcecution() {
		cOperations.sort(Comparator.comparing(COperation::getNegativePriority));
	}
	
	public void addBOperation(BOperation bOperation) {
		this.bOperations.add(bOperation);
	}
	
	public void addAllBOperations(Collection<BOperation> bOperations) {
		this.bOperations.addAll(bOperations);
	}

	public void addCOperation(COperation cOperation) {
		this.cOperations.add(cOperation);
	}
	
	public void addAllCOperations(Collection<COperation> cOperations) {
		this.cOperations.addAll(cOperations);
	}

	protected void setCOperations(List<COperation> emptyCollectionOfCOperations) {
		this.cOperations = emptyCollectionOfCOperations;
	}
	
	protected void setBOperations(Collection<BOperation> emptyCollectionOfBOperations) {
		this.bOperations = emptyCollectionOfBOperations;
	}
}
