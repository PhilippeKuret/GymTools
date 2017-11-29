package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "training_plan", indices = {@Index(value = {"name"}, unique = true)})
public class TrainingPlan
{
	public TrainingPlan()
	{
	}

	public TrainingPlan(String name, boolean isDisplayedPlan)
	{
		Name = name;
		IsDisplayedPlan = isDisplayedPlan;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	@ColumnInfo(name = "is_displayed")
	private boolean IsDisplayedPlan;

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public boolean getIsDisplayedPlan()
	{
		return IsDisplayedPlan;
	}

	public void setIsDisplayedPlan(boolean displayedPlan)
	{
		IsDisplayedPlan = displayedPlan;
	}
}
