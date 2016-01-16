package com.wesimulated.simulationmotor.operationbased;

import hla.rti1516e.exceptions.FederateNotExecutionMember;
import hla.rti1516e.exceptions.InTimeAdvancingState;
import hla.rti1516e.exceptions.InvalidLogicalTime;
import hla.rti1516e.exceptions.LogicalTimeAlreadyPassed;
import hla.rti1516e.exceptions.NotConnected;
import hla.rti1516e.exceptions.RTIinternalError;
import hla.rti1516e.exceptions.RequestForTimeConstrainedPending;
import hla.rti1516e.exceptions.RequestForTimeRegulationPending;
import hla.rti1516e.exceptions.RestoreInProgress;
import hla.rti1516e.exceptions.SaveInProgress;

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
	
	protected void execAPhase() throws LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress, RestoreInProgress, FederateNotExecutionMember, NotConnected, RTIinternalError, InterruptedException {
		BOperationCore firstOperation = bOperations.iterator().next();
		if (firstOperation != null) {
			this.getClock().advanceUntil(firstOperation.getStartTime());
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
	
	public void addAllBOperations(Collection<BOperation> bOperations) {
		this.bOperations.addAll((Collection<BOperationCore>) bOperations.stream().map(bOperation -> { return new BOperationCore(bOperation); }));
	}

	public void addCOperation(COperation cOperation) {
		this.cOperations.add(new COperationCore(cOperation));
	}
	
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
