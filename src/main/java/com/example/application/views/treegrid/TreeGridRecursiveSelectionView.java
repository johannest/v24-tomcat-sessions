package com.example.application.views.treegrid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;

@Route("treegrid")
public class TreeGridRecursiveSelectionView extends VerticalLayout {

    public TreeGridRecursiveSelectionView() {
        this.setSizeFull();
        createBasicTreeGridUsage();
    }

    private void createBasicTreeGridUsage() {
        DepartmentData departmentData = new DepartmentData();

        TreeGrid<Department> grid = new RecursiveSelectTreeGrid<>();

        grid.setItems(departmentData.getRootDepartments(), departmentData::getChildDepartments);
        grid.addHierarchyColumn(Department::getName).setHeader("Department Name");
        grid.addColumn(Department::getManager).setHeader("Manager");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        add(grid);
    }
}
