<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

class CreateTasksTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('tasks', function(Blueprint $table)
		{
			$table->increments('id');
			$table->timestamp('startDate')->default(DB::raw('CURRENT_TIMESTAMP'));
			$table->boolean('durationMinut')->default(60);
			$table->string('title', 100);
			$table->string('detail', 250);
			$table->boolean('isDone')->default(0);
			$table->smallInteger('user_Fk')->unsigned()->index('user_Fk');
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('tasks');
	}

}
